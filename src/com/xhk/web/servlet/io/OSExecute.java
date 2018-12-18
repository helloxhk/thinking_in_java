package com.xhk.web.servlet.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author xhk
 * @time 2018-12-18 13:40
 */
public class OSExecute {

	public static void command(String command) {
		boolean err = false;

		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String str;
			while ((str = in.readLine()) != null)
				System.out.println(str);

			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((str = error.readLine()) != null){
				System.err.println(str);
				err = true;
			}

		} catch (Exception e) {
			if(!command.startsWith("cmd /c"))
				command("cmd /c " + command);
			else
				throw new RuntimeException(e);
		}
		if (err) {
			throw new OSExecuteException("Error executing " + command);
		}
	}
}
