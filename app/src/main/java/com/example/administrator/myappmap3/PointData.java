package com.example.administrator.myappmap3;

/**
 * Created by Administrator on 2017/08/23.
 * table PointHistory data
 * id int,createdate date,latitude double,longitude double,comment varchar(40)
 */

public class PointData
{
    int id;
    String createdate;
    double latitude;
    double longitude;
    String comment;

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedate()
    {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
