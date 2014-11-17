package com.forerunner.core.javatest;


import java.io.IOException;

public class JavaTest {

	public static void main(String[] args) throws IOException {
		String filename = "namejpg";
		int indexOf = filename.indexOf(".") == -1 ? filename.length() : filename.indexOf(".");
		System.out.println(filename.substring(0, indexOf));
		System.out.println(filename.substring(indexOf));
	}
	//	public static void main(String[] sargs) {
	//		TplComponent tplComponent = new TplComponent();
	//		tplComponent.setName("comp1");
	//		TplDatasource datasource = new TplDatasource();
	//		datasource.setName("data1");
	//		tplComponent.setDatasource(datasource);
	//
	//		HashMap<Object, Object> map = Maps.newHashMap();
	//		map.put("component", tplComponent);
	//
	//		Pattern pattern = Pattern.compile("\\$\\{((\\w|\\.|\\(|\\)){1,30})\\}");
	//		Matcher matcher = pattern.matcher("你好${component.datasource.name}哈喽");
	//		StringBuffer sb = new StringBuffer();
	//		while (matcher.find()) {
	//			String field = matcher.group(1);
	//
	//			Binding binding = new Binding();
	//			binding.setVariable("target", map);
	//			GroovyShell shell = new GroovyShell(binding);
	//
	//			field = "target." + field;
	//
	//			System.out.println(field);
	//
	//			Object result = (Object) shell.evaluate(field);
	//			System.out.println("result " + result);
	//
	//			if (result != null)
	//				field = result.toString();
	//
	//			matcher.appendReplacement(sb, field);
	//		}
	//		matcher.appendTail(sb);
	//
	//		System.out.println(sb.toString());
	//	}
	//	public static void main(String[] args) {
	//		ArrayList<String> newArrayList = Lists.newArrayList();
	//		HashSet<String> newHashSet = Sets.newHashSet();
	//		newArrayList.add("1");
	//		newArrayList.add("1");
	//		newHashSet.addAll(newArrayList);
	//		System.out.println(newArrayList);
	//		System.out.println(newHashSet);
	//	}

	//	public static void main(String[] argv) throws Exception {
	//		CompanyemailService emailService = new CompanyemailService();
	//		String token = emailService.token();
	//		emailService.authkey(token);
	//
	//		String host = URLEncoder.encode("host", "UTF-8") + "=" + URLEncoder.encode("openapi.exmail.qq.com", "UTF-8");
	//		String access_token = URLEncoder.encode("access_token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
	//		String ver = URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
	//		final String args = host + "&" + access_token + "&" + ver;
	//
	//		String path = "/openapi/listen";
	//
	//		final Socket socket = new Socket("openapi.exmail.qq.com", 12211);
	//
	//		socket.setSoTimeout(5000);
	//
	//		final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	//		writer.write("POST " + path + " HTTP/1.1\r\n");
	//		writer.write("Host: openapi.exmail.qq.com:12211\r\n");
	//		writer.write("Content-Type: application/x-www-form-urlencoded; charset=UTF-8\r\n");
	//		writer.write("Content-Length: " + args.length() + "\r\n");
	//		writer.write("\r\n");
	//		writer.write(args);
	//		writer.flush();
	//
	//		final InputStream in2 = socket.getInputStream();
	//
	//		final InputStreamReader in = new InputStreamReader(in2);
	//		final BufferedReader reader = new BufferedReader(in);
	//		new Thread(new Runnable() {
	//
	//			private byte[] convert(char[] data) {
	//				byte[] datab = new byte[data.length];
	//				for (int index = 0; index < data.length; index++) {
	//					datab[index] = (byte) data[index];
	//				}
	//				return datab;
	//			}
	//
	//			private int getContentLength(List<String> heads) {
	//				for (String hd : heads) {
	//					if (hd.indexOf("Content-Length: ") != -1) {
	//						String slen = hd.substring("Content-Length: ".length());
	//						System.out.println(slen);
	//						return new Integer(slen);
	//					}
	//				}
	//				for (String hd : heads) {
	//					System.out.println(hd);
	//				}
	//				System.out.println("none");
	//				return 0;
	//			}
	//
	//			private void sendListenPacket(final BufferedWriter writer) throws UnsupportedEncodingException, IOException {
	//				CompanyemailService emailService = new CompanyemailService();
	//				String token = emailService.token();
	//				emailService.authkey(token);
	//				String host = URLEncoder.encode("host", "UTF-8") + "=" + URLEncoder.encode("openapi.exmail.qq.com", "UTF-8");
	//				String access_token = URLEncoder.encode("access_token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
	//				String ver = URLEncoder.encode("ver", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
	//				final String args = host + "&" + access_token + "&" + ver;
	//
	//				String path = "/openapi/listen";
	//				writer.write("POST " + path + " HTTP/1.1\r\n");
	//				writer.write("Host: openapi.exmail.qq.com:12211\r\n");
	//				writer.write("Content-Type: application/x-www-form-urlencoded; charset=UTF-8\r\n");
	//				writer.write("Content-Length: " + args.length() + "\r\n");
	//				writer.write("\r\n");
	//				writer.write(args);
	//				writer.flush();
	//			}
	//
	//			@Override
	//			public void run() {
	//				while (!socket.isClosed()) {
	//					try {// for read time out
	//
	//						int by;// first char
	//						while ((by = in.read()) != -1) {
	//							String r = "\r";
	//							String n = "\n";
	//							String rn = "\r\n";
	//							StringBuilder builder = new StringBuilder();
	//							ArrayList<String> heads = Lists.newArrayList();
	//
	//							String body = null;
	//							boolean flag = true;
	//							do {
	//								String curr = new String(new byte[] { (byte) by }, "utf-8");
	//								if (r.equals(curr)) {
	//									curr = new String(new byte[] { (byte) in.read() }, "utf-8");
	//									if (n.equals(curr)) {
	//
	//										String head = builder.toString();
	//										System.out.println(head);
	//
	//										if (builder.length() == 0) {// when \r\n\r\n
	//											char[] data = new char[getContentLength(heads)];
	//											in.read(data);
	//											body = new String(convert(data), "utf-8");
	//
	//											//process business
	//											System.out.println("incoming data : " + body.replace(rn, ""));
	//
	//											flag = false;//end business
	//
	//										} else {// when head
	//											heads.add(head);
	//										}
	//
	//										builder.delete(0, builder.length());// clear buffer
	//									}
	//								} else {// when != \r\n
	//									builder.append(curr);// one by one append char to String builder (all head char)
	//								}
	//
	//								if (flag) {
	//									by = in.read();// read next char (all head char)
	//								}
	//							} while (flag);
	//						}
	//						Thread.sleep(1000);
	//					} catch (SocketTimeoutException e) {
	//						try {
	//							sendListenPacket(writer);
	//						} catch (Exception ee) {
	//							ee.printStackTrace();
	//						}
	//					} catch (Exception e) {
	//						e.printStackTrace();
	//					}
	//				}
	//				try {// close res
	//					writer.close();
	//					reader.close();
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}).start();
	//	}

	//	public void poller() {
	//		new Thread(new Runnable() {
	//
	//			@Override
	//			public void run() {
	//				Long start = System.currentTimeMillis();
	//				Long end = 0L;
	//				while (end <= start + 30000) {
	//					end = System.currentTimeMillis();
	//				}
	//			}
	//		}).start();
	//	}

	//	public static void main(String[] args) {
	//		ExecutorService executor = Executors.newFixedThreadPool(1);
	//
	//		Future<String> future = executor.submit(new Callable<String>() {
	//			@Override
	//			public String call() throws Exception {
	//				System.out.println("call start");
	//				Thread.sleep(2000);
	//				System.out.println("call end");
	//				return "the data be return.";
	//			}
	//		});
	//
	//		try {
	//
	//			System.out.println("@@@@@@@@@");//execute
	//			System.out.println(future.get());//block(synchronization)
	//
	//		} catch (InterruptedException e) {
	//			e.printStackTrace();
	//		} catch (ExecutionException e) {
	//			e.printStackTrace();
	//		}
	//
	//		executor.shutdown();
	//	}
}