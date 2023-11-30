/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.loadPg;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yifuyou.web.Constants;
import com.yifuyou.web.databinding.LoadItemInfoBinding;
import com.yifuyou.web.db.DataBaseUtil;
import com.yifuyou.web.load.LoadFileRecord;
import com.yifuyou.web.util.CommandUtil;
import com.yifuyou.web.util.DownloadUtil;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class LoadRcAdapter extends RecyclerView.Adapter<LoadRcAdapter.LoadItemViewHolder> {
    private static final String TAG = "LoadRcAdapter";

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
            Log.d(TAG, "LoadRcAdapter: "+ lf.toString());
        });
        mHandler = handler;

        mData.sort((f1, f2)->{
            if (f1.getlTime() != 0 && f2.getlTime() != 0) {
                return (int)(f1.getlTime() - f2.getlTime());
            }

            if (f1.getsTime() != 0 && f2.getsTime() != 0) {
                return (int)(f1.getsTime() - f2.getsTime());
            }
            return 1;
        });

    }


    @NonNull
    @Override
    public LoadItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LoadItemInfoBinding dataBinding =
            LoadItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LoadItemViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadItemViewHolder holder, int position) {
        LoadFileRecord loadFileRecord = mData.get(position);
        setItemView(holder, loadFileRecord, false);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: ");
                if (DownloadUtil.resumeLoad(loadFileRecord)) {
                    holder.startIndex = loadFileRecord.getLoadLength();
                    setItemView(holder, loadFileRecord, true);
                }
                return false;
            }
        });
    }

    private void setItemView(LoadItemViewHolder holder, LoadFileRecord loadFileRecord, boolean isResume){
        holder.mBinding.itemName.setText(loadFileRecord.getName());
        if (loadFileRecord.isFinished()) {
            holder.mBinding.itemState.setText(loadFileRecord.getState());
            holder.mBinding.itemProgress.setVisibility(View.GONE);
            holder.mBinding.itemLoading.setText(CommandUtil.getTimeByLong(loadFileRecord.getlTime()));
            return;
        }

        setStateAndProgress(holder.mBinding, loadFileRecord, loadFileRecord.getLoadLength());
        holder.mBinding.itemState.setText(loadFileRecord.getState());
        loadFileRecord.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                mHandler.post(()->{
                    long newCount = (long)arg;
                    if (isResume) {
                        newCount += holder.startIndex;
                    }
                    setStateAndProgress(holder.mBinding, loadFileRecord, newCount);
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
    public void onViewDetachedFromWindow(@NonNull LoadItemViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i(TAG, "onViewDetachedFromWindow: ");
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        Log.i(TAG, "onDetachedFromRecyclerView: ");
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size():0;
    }

    public class LoadItemViewHolder extends RecyclerView.ViewHolder{
        LoadItemInfoBinding mBinding;

        long startIndex = 0;

        public LoadItemViewHolder(@NonNull LoadItemInfoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
