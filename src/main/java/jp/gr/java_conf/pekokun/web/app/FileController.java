package jp.gr.java_conf.pekokun.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("file")
public class FileController {

	@GetMapping
	public String index(Model model) {
		return "file/index";
	}

	@PostMapping
	public String upload(MultipartFile file, Model model) {
		
		FileDetail fileDetail = new FileDetail(file.getOriginalFilename(), file.getSize());
		model.addAttribute("file", fileDetail);

		return index(model);
	}

	public static class FileDetail {
		private final String name;
		private final long size;
		
		public FileDetail(String name, long size) {
			this.name = name;
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public long getSize() {
			return size;
		}
	}
}
