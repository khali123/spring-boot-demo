package jp.gr.java_conf.pekokun.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jp.gr.java_conf.pekokun.util.ByteSizeUnit;

public class MultipartFileInterceptor extends HandlerInterceptorAdapter {

	private final long maxFileSize;

	public MultipartFileInterceptor(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
	
	public MultipartFileInterceptor(String maxFileSize) {
		this(ByteSizeUnit.toBytes(maxFileSize));
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) throws Exception {

		if (MultipartRequest.class.isInstance(request) && maxFileSize >= 0) {
			MultipartRequest multipartRequest = MultipartRequest.class.cast(request);

			multipartRequest.getFileMap()
				.values()
				.stream()
				.forEach(f -> {
					if (f.getSize() > maxFileSize) {
						throw new MaxUploadSizeExceededException(maxFileSize);
					}
				});
		}

		return super.preHandle(request, response, handler);
	}
}
