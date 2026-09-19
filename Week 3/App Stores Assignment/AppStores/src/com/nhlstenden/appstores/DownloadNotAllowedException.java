package com.nhlstenden.appstores;

public class DownloadNotAllowedException extends Exception
{
    public DownloadNotAllowedException(String message)
    {
        super(message);
    }
}