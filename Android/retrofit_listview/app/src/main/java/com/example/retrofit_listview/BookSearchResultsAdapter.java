package com.example.retrofit_listview;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_listview.data.Volume;

import java.util.ArrayList;
import java.util.List;

public class BookSearchResultsAdapter extends RecyclerView.Adapter<BookSearchResultsAdapter.BookHolder>
{
    private List<Volume> results = new ArrayList<>();
    private ItemClickListener clickListener;
    public interface ItemClickListener {
        void onClick(View v, String id);
    }
    public void setClickListener(ItemClickListener itemClickListener){
        this.clickListener=itemClickListener;
    }
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultsAdapter.BookHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);
        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String autores = "";
            for(String a :volume.getVolumeInfo().getAuthors()){
                autores += a+", ";
            }
            holder.authorsTextView.setText(autores);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }
    public class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                Log.d(TAG, results.get(getAdapterPosition()).getId());
                clickListener.onClick(v, results.get(getAdapterPosition()).getId());
            }
        }
    }
}
