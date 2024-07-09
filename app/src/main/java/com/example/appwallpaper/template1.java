package com.example.appwallpaper;

import android.app.Fragment;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class template1 extends Fragment {

    private static final int REQUEST_SET_WALLPAPER = 101;

    private int selectedImageResId;
    private GridView gridView;
    private ImageAdapter imageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_template1, container, false);

        gridView = rootView.findViewById(R.id.gridView);

        List<Integer> imagesList = getImages();
        imageAdapter = new ImageAdapter(getActivity(), imagesList);
        gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(), "Image clicked: " + i, Toast.LENGTH_SHORT).show();
                selectedImageResId = imagesList.get(i);
                checkPermissionAndSetWallpaper();
            }
        });

        return rootView;
    }

    private List<Integer> getImages() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.cell1);
        images.add(R.drawable.cell2);
        images.add(R.drawable.cell3);
        images.add(R.drawable.cell4);
        images.add(R.drawable.cell5);
        images.add(R.drawable.cell6);
        images.add(R.drawable.cell7);

        return images;
    }

    private void checkPermissionAndSetWallpaper() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.SET_WALLPAPER) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.SET_WALLPAPER}, REQUEST_SET_WALLPAPER);
        } else {
            // Permission already granted, proceed to set wallpaper
            setWallpaper();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SET_WALLPAPER) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed to set wallpaper
                setWallpaper();
            } else {
                // Permission denied
                Toast.makeText(getActivity().getApplicationContext(), "Permission denied to set wallpaper", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setWallpaper() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), selectedImageResId);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getActivity());
        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(getActivity().getApplicationContext(), "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(), "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }
}
