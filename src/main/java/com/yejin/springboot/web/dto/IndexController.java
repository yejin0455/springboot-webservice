package com.yejin.springboot.web.dto;

import com.yejin.springboot.config.auth.LoginUser;
import com.yejin.springboot.config.auth.dto.SessionUser;
import com.yejin.springboot.web.PostsResponseDto;
import com.yejin.springboot.web.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {  //어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있음
        //기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값 변경
        model.addAttribute("posts", postsService.findAllDesc());
        //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함
        if (user != null) {  //세션에 저장된 값이 있을 때만 model에 userName으로 등록함, 없으면 로그인 버튼이 보임
            model.addAttribute("userName", user.getName());
        }
        return "index";  //앞의 경로와 뒤의 파일 확장자 자동 지정 -> src/main/resources/templates/index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";  // src/main/resources/templates/posts-save.mustache
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
