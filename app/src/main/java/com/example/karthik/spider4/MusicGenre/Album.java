package com.example.karthik.spider4.MusicGenre;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by karthik on 26-06-2018.
 */
public class Album {

    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("album_mbid")
    @Expose
    private String albumMbid;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("album_rating")
    @Expose
    private Integer albumRating;
    @SerializedName("album_track_count")
    @Expose
    private Integer albumTrackCount;
    @SerializedName("album_release_date")
    @Expose
    private String albumReleaseDate;
    @SerializedName("album_release_type")
    @Expose
    private String albumReleaseType;
    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("primary_genres")
    @Expose
    private PrimaryGenres primaryGenres;
    @SerializedName("secondary_genres")
    @Expose
    private SecondaryGenres secondaryGenres;
    @SerializedName("album_pline")
    @Expose
    private String albumPline;
    @SerializedName("album_copyright")
    @Expose
    private String albumCopyright;
    @SerializedName("album_label")
    @Expose
    private String albumLabel;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("album_coverart_100x100")
    @Expose
    private String albumCoverart100x100;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumMbid() {
        return albumMbid;
    }

    public void setAlbumMbid(String albumMbid) {
        this.albumMbid = albumMbid;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getAlbumRating() {
        return albumRating;
    }

    public void setAlbumRating(Integer albumRating) {
        this.albumRating = albumRating;
    }

    public Integer getAlbumTrackCount() {
        return albumTrackCount;
    }

    public void setAlbumTrackCount(Integer albumTrackCount) {
        this.albumTrackCount = albumTrackCount;
    }

    public String getAlbumReleaseDate() {
        return albumReleaseDate;
    }

    public void setAlbumReleaseDate(String albumReleaseDate) {
        this.albumReleaseDate = albumReleaseDate;
    }

    public String getAlbumReleaseType() {
        return albumReleaseType;
    }

    public void setAlbumReleaseType(String albumReleaseType) {
        this.albumReleaseType = albumReleaseType;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public PrimaryGenres getPrimaryGenres() {
        return primaryGenres;
    }

    public void setPrimaryGenres(PrimaryGenres primaryGenres) {
        this.primaryGenres = primaryGenres;
    }

    public SecondaryGenres getSecondaryGenres() {
        return secondaryGenres;
    }

    public void setSecondaryGenres(SecondaryGenres secondaryGenres) {
        this.secondaryGenres = secondaryGenres;
    }

    public String getAlbumPline() {
        return albumPline;
    }

    public void setAlbumPline(String albumPline) {
        this.albumPline = albumPline;
    }

    public String getAlbumCopyright() {
        return albumCopyright;
    }

    public void setAlbumCopyright(String albumCopyright) {
        this.albumCopyright = albumCopyright;
    }

    public String getAlbumLabel() {
        return albumLabel;
    }

    public void setAlbumLabel(String albumLabel) {
        this.albumLabel = albumLabel;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getAlbumCoverart100x100() {
        return albumCoverart100x100;
    }

    public void setAlbumCoverart100x100(String albumCoverart100x100) {
        this.albumCoverart100x100 = albumCoverart100x100;
    }

}
