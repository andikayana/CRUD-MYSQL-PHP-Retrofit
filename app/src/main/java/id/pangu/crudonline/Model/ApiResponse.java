package id.pangu.crudonline.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse{

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("kode")
	private int kode;

	@SerializedName("pesan")
	private String pesan;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}

	@Override
	public String toString() {
		return "ApiResponse{" +
				"result=" + result +
				", kode=" + kode +
				", pesan='" + pesan + '\'' +
				'}';
	}
}