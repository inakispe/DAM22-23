package com.example.simpsonapi.Datos;

import retrofit2.http.GET;

public interface QuoteServices {
    @GET("quotes/{num}")
    Call
}
