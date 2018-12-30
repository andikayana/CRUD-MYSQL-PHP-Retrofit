package id.pangu.crudonline.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResultItem implements Parcelable {

	@SerializedName("usia")
	private String usia;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("domisili")
	private String domisili;

	public void setUsia(String usia){
		this.usia = usia;
	}

	public String getUsia(){
		return usia;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDomisili(String domisili){
		this.domisili = domisili;
	}

	public String getDomisili(){
		return domisili;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"usia = '" + usia + '\'' + 
			",nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",domisili = '" + domisili + '\'' + 
			"}";
		}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.usia);
		dest.writeString(this.nama);
		dest.writeString(this.id);
		dest.writeString(this.domisili);
	}

	public ResultItem() {
	}

	protected ResultItem(Parcel in) {
		this.usia = in.readString();
		this.nama = in.readString();
		this.id = in.readString();
		this.domisili = in.readString();
	}

	public static final Parcelable.Creator<ResultItem> CREATOR = new Parcelable.Creator<ResultItem>() {
		@Override
		public ResultItem createFromParcel(Parcel source) {
			return new ResultItem(source);
		}

		@Override
		public ResultItem[] newArray(int size) {
			return new ResultItem[size];
		}
	};
}