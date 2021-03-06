package com.example.saito.imageloadlibrarytest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by saito on 2016/03/14.
 */
public class ListViewAdapter extends ArrayAdapter<ListViewModel>{

    private static final String TAG = ListViewAdapter.class.getSimpleName();

    // Viewにタグ付けするオブジェクトとして、ViewHolderというものを作る
    private static class ViewHolder {
        public TextView nameTextView;
        public TextView hobbyTextView;
        public ImageView thumbnailBitmap;

        /**
         *
         * @param v
         */
        public ViewHolder(View v) {
            super();
            this.nameTextView = (TextView) v.findViewById(R.id.textName);
            this.hobbyTextView = (TextView) v.findViewById(R.id.textHobby);
            this.thumbnailBitmap = (ImageView) v
                    .findViewById(R.id.imageThumbnail);
        }
    }

    private LayoutInflater layoutInflater;

    private Context mContext;
    public ListViewAdapter(Context context, int resource, List<ListViewModel> objects) {
        super(context, resource, objects);
        mContext = context;
        this.layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Viewがリサイクル出来ない場合
        if (convertView == null) {
            // Viewにレイアウトの情報を登録する
            convertView = layoutInflater.inflate(R.layout.list_base, null);
            // Viewがリサイクル出来る場合はnewする必要がないので、holderはここでnewする
            holder = new ViewHolder(convertView);
            // リサイクルするときのためにタグ付けしておく
            convertView.setTag(holder);
        } else { // Viewがリサイクル出来る場合
            // タグ付けしておいた情報を取得する
            holder = (ViewHolder) convertView.getTag();
        }

        //
        ListViewModel item = getItem(position);
        holder.nameTextView.setText(item.getName());
        holder.hobbyTextView.setText(item.getHobby());

        switch (item.getLibraryFlg()) {
            case PICASSO:
                imageLoadPicasso(holder, item);
                break;
            case GLIDE:
                imageLoadGlide(holder, item);
                break;
        }



        return convertView; //view を返す
    }

    private void imageLoadGlide(ViewHolder holder, ListViewModel item) {
        Glide.with(mContext)
                .load(item.getThumbnailUrl())
                // リサイズ（縦横の最大サイズを指定して、収める）
//                .override(600, 600)
                .placeholder(android.R.drawable.ic_menu_call)   // ローディング画像
                .error(android.R.drawable.ic_delete)    // エラー画像
                // placeholderを設定した場合に必要 これを書かないとplaceholder画像と同じ大きさにリサイズされる
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        // イメージ取得エラーコールバック
                        Log.e(TAG, "onException", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        // イメージ取得コールバック
                        Log.v(TAG, "onResourceReady  resource:" + resource + " model:" + model + " isFromMemoryCache:" + isFromMemoryCache + " isFirstResource:" +isFirstResource);
                        return false;
                    }
                })
                .into(holder.thumbnailBitmap);  // imageViewに投入
    }
    private void imageLoadPicasso(ViewHolder holder, ListViewModel item) {
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                // イメージ取得エラーコールバック
                Log.e(TAG, "ImageLoadError", exception);
            }

        });
        Picasso picasso = builder.build();
        picasso.with(mContext)
                .load(item.getThumbnailUrl())
                .placeholder(android.R.drawable.ic_menu_call)   // ローディング画像
                .error(android.R.drawable.ic_delete)    // エラー画像
                .into(holder.thumbnailBitmap);  // imageViewに投入
    }
}
