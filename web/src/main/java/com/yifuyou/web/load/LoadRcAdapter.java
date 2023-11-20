/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.load;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yifuyou.web.databinding.LoadItemInfoBinding;
import com.yifuyou.web.load.db.DataBaseUtil;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class LoadRcAdapter extends RecyclerView.Adapter<LoadRcAdapter.ViewHolder> {
    List<LoadFileRecord> mData;

    Handler mHandler;

    public LoadRcAdapter(Handler handler) {
        mData = DownloadUtil.getLoadingRecord();
        DataBaseUtil.getAllLoadFileRecord().forEach(loadFileRecord -> {
            if (!mData.contains(loadFileRecord)) {
                mData.add(loadFileRecord);
            }
        });
        mData.forEach(lf ->{
            Log.d("TAG", "LoadRcAdapter: "+ lf.toString());
        });
        mHandler = handler;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LoadItemInfoBinding dataBinding =
                LoadItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoadFileRecord loadFileRecord = mData.get(position);
        holder.mBinding.itemName.setText(loadFileRecord.getName());
        if (Constants.STATE_STRING_LOAD_SUCCESS.equals(loadFileRecord.getState()) ||
                Constants.STATE_STRING_LOAD_FAILED.equals(loadFileRecord.getState())) {
            holder.mBinding.itemState.setText(loadFileRecord.getState());
            holder.mBinding.itemProgress.setVisibility(View.GONE);
            holder.mBinding.itemLoading.setText(CommandUtil.getTimeByLong(loadFileRecord.getlTime()));
            return;
        }
        setStateAndProgress(holder.mBinding, loadFileRecord, 0L);
        holder.mBinding.itemState.setText(Constants.STATE_STRING_LOADING);
        loadFileRecord.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                mHandler.post(()->{
                        setStateAndProgress(holder.mBinding, loadFileRecord, (long)arg);
                });
            }
        });
    }

    private void setStateAndProgress(LoadItemInfoBinding binding, LoadFileRecord loadFileRecord, long newLen) {
        binding.itemLoading.setText(newLen + "/" + loadFileRecord.getFileLength());
        double pros = newLen / (double)loadFileRecord.getFileLength();
        binding.itemProgress.setProgress((int)(((int)(pros * 1000))/10.0f));
        if(newLen == loadFileRecord.getFileLength()) {
            binding.itemState.setText(Constants.STATE_STRING_LOAD_SUCCESS);
            binding.itemLoading.setText("");
        }
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LoadItemInfoBinding mBinding;

        public ViewHolder(@NonNull LoadItemInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
