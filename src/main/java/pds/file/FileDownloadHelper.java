package pds.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileDownloadHelper {
	//특정결로의 파일 데이터를 OutputStream으로 출력하는 기능
	public static void copy(String filePath, OutputStream os) throws IOException{
		FileInputStream is = null;
		
		try {
			is = new FileInputStream(filePath); //파일에서 데이터를 읽어오는 inputStream 객체 생성
			byte[] data = new byte[8096];
			int len = -1;
			while((len = is.read(data)) != -1) {//데이터를 읽어와 출력 스트림(os)에 데이터 출력
				os.write(data, 0, len);
			}
		}finally {
			if(is != null) {
				try {
					is.close();
				}catch(IOException e) {
					
				}
			}
		}
	}
}
