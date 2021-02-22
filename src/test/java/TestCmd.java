import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestCmd {

	public static void main(String[] args) throws Exception {
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c","ffmpeg -y -i \"C:\\Users\\lucky god\\Videos\\video\\0dcc6b7eaf404ea9a53da46f30915d01.mp4\" 0dcc6b7eaf404ea9a53da46f30915d01-format.mp4");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		// 记录进程缓存错误信息
		final StringBuffer errorLog = new StringBuffer();
		final StringBuffer inLog = new StringBuffer();
		// 获取执行进程的错误流
		final InputStream errorStream = process.getErrorStream();
		final InputStream inputStream = process.getInputStream();
		// 处理InputStream的线程
		new Thread() {
			public void run() {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						inputStream));
				String line = null;
				try {
					while ((line = in.readLine()) != null) {
						if (line != null) {
							inLog.append(line);
						}
					}
				} catch (IOException e) {
					throw new RuntimeException(
							"[shell exec error]:" + errorLog, e);
				} finally {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		// 处理errorStream的线程
		new Thread() {
			@Override
			public void run() {
				BufferedReader err = new BufferedReader(new InputStreamReader(
						errorStream));
				String line = null;
				try {
					while ((line = err.readLine()) != null
							&& !errorLog.toString().contains("ERROR")) {
						if (line != null) {
							errorLog.append(line);
						}
					}
				} catch (IOException e) {
					throw new RuntimeException(
							"[shell exec error]:" + errorLog, e);
				} finally {
					try {
						errorStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		System.out.println("等待shell脚本执行完成");
		Thread.sleep(5000);
		// 异常终止
		if (errorLog != null && errorLog.length() > 0
				&& errorLog.toString().contains("ERROR")) {
			System.out.println("[shell exec error]:" + errorLog);
			throw new RuntimeException("[shell exec error]:" + errorLog);
		}
		if (inLog != null && inLog.length() > 0) {
			System.out.println("[shell exec info]:" + inLog);
		}
		process.waitFor(); // 等待shell脚本执行完成

	}

}
