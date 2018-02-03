package com.google.firebase.example;

import java.text.DecimalFormat;

import com.google.api.client.googleapis.media.MediaHttpDownloader.DownloadState;

public class LocationUtil {
	public static final int FORMAT_DEGREES = 0;
	public static final int FORMAT_MINUTES = 1;
	public static final int FORMAT_SECONDS = 2;
	
	public static String convertLatitudeDegree(double latitude){
        StringBuilder builder = new StringBuilder();

        String latitudeDegrees = LocationUtil.convert(Math.abs(latitude), LocationUtil.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builder.append(latitudeSplit[0]);
        builder.append("°");
        builder.append(latitudeSplit[1]);
        builder.append("'");
        builder.append(latitudeSplit[2]);
        builder.append("\"");

        if (latitude < 0) {
            builder.append("S");
        } else {
            builder.append("N");
        }

        return builder.toString();
    }

    public static String convertLongitudeDegree(double longitude) {
        StringBuilder builder = new StringBuilder();

        String longitudeDegrees = LocationUtil.convert(Math.abs(longitude), LocationUtil.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builder.append(longitudeSplit[0]);
        builder.append("°");
        builder.append(longitudeSplit[1]);
        builder.append("'");
        builder.append(longitudeSplit[2]);
        builder.append("\"" );

        if (longitude < 0) {
            builder.append("W");
        } else {
            builder.append("E");
        }

        return builder.toString();
    }
	
	public static String convert(double coordinate, int outputType) {
        if (coordinate < -180.0 || coordinate > 180.0 ||
            Double.isNaN(coordinate)) {
            throw new IllegalArgumentException("coordinate=" + coordinate);
        }
        if ((outputType != FORMAT_DEGREES) &&
            (outputType != FORMAT_MINUTES) &&
            (outputType != FORMAT_SECONDS)) {
            throw new IllegalArgumentException("outputType=" + outputType);
        }
        StringBuilder sb = new StringBuilder();
        // Handle negative values
        if (coordinate < 0) {
            sb.append('-');
            coordinate = -coordinate;
        }
        DecimalFormat df = new DecimalFormat("###.#####");
        if (outputType == FORMAT_MINUTES || outputType == FORMAT_SECONDS) {
            int degrees = (int) Math.floor(coordinate);
            sb.append(degrees);
            sb.append(':');
            coordinate -= degrees;
            coordinate *= 60.0;
            if (outputType == FORMAT_SECONDS) {
                int minutes = (int) Math.floor(coordinate);
                sb.append(minutes);
                sb.append(':');
                coordinate -= minutes;
                coordinate *= 60.0;
            }
        }
        sb.append(df.format(coordinate));
        return sb.toString();
    }
	
	public static String GetNextPrePointByLatLngDegree(String preItem, String nextItem){
        if(preItem != null)
        {
            StringBuilder prePoint = new StringBuilder();
            prePoint.append(preItem);
            prePoint.append(" ");
            prePoint.append(nextItem);

            return prePoint.toString();
        }

        return "";
    }
}
