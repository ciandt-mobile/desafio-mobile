package com.ceandt.moviedb;

import android.content.Context;
import android.content.SharedPreferences;

import com.ceandt.moviedb.preferences.Preferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class PreferenceTest {

    private Context mContext;

    @Before
    public void before() {
        mContext = Mockito.mock(Context.class);
        SharedPreferences mock = Mockito.mock(SharedPreferences.class);
        Mockito.when(Preferences.getSharedPreferences(mContext)).thenReturn(mock);
        Mockito.when(Preferences.getMoviePreference(mContext)).thenReturn(Preferences.POPULAR_VALUE);
    }

    @Test
    public void shouldReturnDefaultPreference() {
        assertEquals(Preferences.getMoviePreference(mContext), Preferences.POPULAR_VALUE);
    }

}