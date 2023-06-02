package com.example.retrofit_listview.api;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_listview.VolumesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL="https://www.googleapis.com/";
    BookSearchService bookSearchService;

    private MutableLiveData<VolumesResponse>volumesResponseMutableLiveData;

    public BookRepository() {
     volumesResponseMutableLiveData= new MutableLiveData<>();
     bookSearchService= new Retrofit.Builder()
             .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(BookSearchService.class);
    }

    public MutableLiveData<VolumesResponse> getVolumesResponseMutableLiveData() {
        return volumesResponseMutableLiveData;
    }
    public void searchVolumes(String keyword, String author){
        bookSearchService.searchVolumes(keyword, author).enqueue(new Callback<com.example.retrofit_listview.data.VolumesResponse>() {
            @Override
            public void onResponse(Call<com.example.retrofit_listview.data.VolumesResponse> call, Response<com.example.retrofit_listview.data.VolumesResponse> response) {

            }

            @Override
            public void onFailure(Call<com.example.retrofit_listview.data.VolumesResponse> call, Throwable t) {

            }
        });

    }
}
