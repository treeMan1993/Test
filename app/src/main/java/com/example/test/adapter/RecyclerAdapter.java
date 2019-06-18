package com.example.test.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView万能适配器
 * Created by ${SJH} on 2018/4/26.
 * 1. 点击事件
 * 2. 数据绑定
 * 3. 传入布局
 * 4. 万能ViewHoder
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHoder> {
    private List<T> datas;
    private AdapterClick<T> adapterClick;
    private int layoutId;
    private int noDataLayoutId;
    private int EMPTY_ITEM_FLAGE = -1;
    private int index = 0;

    public RecyclerAdapter(int layoutId, List<T> datas) {
        this.layoutId = layoutId;
        this.datas = datas;
    }

    /**
     * 调用该构造函数可以传一个无数据时的占位item，当没有数据的时候，该item自动显示。
     *
     * @param layoutId       正常布局id
     * @param datas          数据集
     * @param noDataLayoutId 无数据占位item布局id
     */
    public RecyclerAdapter(int layoutId, List<T> datas, int noDataLayoutId) {
        this.layoutId = layoutId;
        this.datas = datas;
        this.noDataLayoutId = noDataLayoutId;
    }

    public void setAdapterClick(AdapterClick<T> adapterClick) {
        this.adapterClick = adapterClick;
    }

    public void setNewDatas( List<T> datas) {
        this.datas = datas == null ? new ArrayList<T>() : datas;
        notifyDataSetChanged();
    }

    /**
     * 匹配界面方法
     *
     * @param viewType 区分局界面加载标识
     *                 0:  默认数据item界面
     *                 EMPTY_ITEM_FLAGE: 空数据占位item
     *                 其它: 通过createViewHolder方法里拿到的viewType，可以加载多个不同的item。
     * @return
     */
    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerAdapter.ViewHoder holder = null;
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            holder = new RecyclerAdapter.ViewHoder(view);
        } else if (viewType == EMPTY_ITEM_FLAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(noDataLayoutId, parent, false);
            holder = new RecyclerAdapter.ViewHoder(view);
        } else {
            holder = CreateViewHolder(parent, viewType);
        }
        return holder;
    }

    /**
     * 该抽象方法是用来适配界面数据
     *
     * @param viewHoder 当前显示的ViewHoder
     * @param item      当前的数据
     * @param position  当前行数
     */
    public abstract void bindViewHolder(ViewHoder viewHoder, T item, int position);

    /**
     * 该方法用于加载不同布局时提供viewType，如果重写该方法的返回值，就必须重写CreateViewHolder写上自己的item加载逻辑。
     *
     * @param item     当前item的数据
     * @param position 当前item的位置
     * @return 当前item的ViewType
     */
    public int returnItemViewType(T item, int position) {
        return 0;
    }

    public ViewHoder CreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 该方法在每次调用notifyDataSetChanged刷新数据时，都会调用。
     * 如果数据量为0，并且使用构造函数时传了一个占位item，就返回-1
     *
     * @param position 当前数据下标
     * @return 返回界面识别表示。
     */
    @Override
    public int getItemViewType(int position) {
        if (noDataLayoutId != 0 && datas.size() == 0) {
            return EMPTY_ITEM_FLAGE;
        } else {
            return returnItemViewType(datas.get(position), position);
        }
    }

    /**
     * 该方法每次调用适配器的 notifyDataSetChanged 方法刷新数据时都会被调用
     * 如果构造函数里传了占位item，需要在数据量为0时，需要一个占位item的位置。
     *
     * @return 数据有多少个
     */
    @Override
    public int getItemCount() {
        if (noDataLayoutId != 0) {
            return datas == null || datas.size() == 0 ? 1 : datas.size();
        } else {
            return datas == null ? 0 : datas.size();
        }
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapterClick != null && datas.size() > 0) {
                    adapterClick.Click(v, position, datas.get(position));
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (adapterClick != null && datas.size() > 0) {
                    adapterClick.LongClick(v, position, datas.get(position));
                }
                return true;
            }
        });
        if (datas.size() > 0) {
            bindViewHolder(holder, datas.get(position), position);
        }
    }

    public static class ViewHoder extends RecyclerView.ViewHolder {

        private SparseArray<View> views;

        public ViewHoder(View itemView) {
            super(itemView);
            views = new SparseArray<>(8);
        }

        public SparseArray<View> getViews() {
            return views;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        public ViewHoder setTextString(int viewId, String data) {
            TextView tv = getView(viewId);
            if (tv != null) {
                if (!TextUtils.isEmpty(data)) {
                    tv.setText(data);
                } else {
                    tv.setText("");
                }
            }
            return this;
        }

        public ViewHoder setViewVisible(int viewId, int visible) {
            View view = getView(viewId);
            if (view != null) {
                view.setVisibility(visible);
            }
            return this;
        }

        public ViewHoder setBackColor(int viewId, int color) {
            View view = getView(viewId);
            if (view != null) {
                view.setBackgroundColor(color);
            }
            return this;
        }

        public ViewHoder setTextColor(int viewId, int color) {
            TextView view = getView(viewId);
            if (view != null) {
                view.setTextColor(color);
            }
            return this;
        }

        public ViewHoder setBackground(int viewId, Drawable drawable) {
            View view = getView(viewId);
            if (view != null && drawable != null) {
                view.setBackground(drawable);
            }
            return this;
        }

        public ViewHoder setBackground(int viewId, int drawableId, Context context) {
            View view = getView(viewId);
            if (view != null) {
                view.setBackground(context.getResources().getDrawable(drawableId));
            }
            return this;
        }

        public ViewHoder setVisibility(int viewId, int state) {
            View view = getView(viewId);
            if (view != null) {
                view.setVisibility(state);
            }
            return this;
        }

        /**
         * 给item里面的控件设置点击事件，
         *
         * @param viewId   需要点击事件的控件Id
         * @param listener 点击事件
         * @return 如果通过传入的id没有找到控件就返回false
         */
        public boolean setItemViewClick(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            if (view != null) {
                view.setOnClickListener(listener);
                return true;

            } else {
                return false;
            }
        }
    }

}
