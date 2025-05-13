package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public String index(
			@RequestParam(name="keyword", defaultValue="") String keyword,
			Model model) {
		List<User> userList = new ArrayList<>();
		
		if(keyword != null && keyword.length() > 0) {
			userList = userRepository.findByKeyword("%"+ keyword +"%");
		}
		else {
			userList = userRepository.findAll();
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("userList", userList);
		return "users";
	}
	
	//＝＝ 登録 ＝＝
	@GetMapping("/users/add")
	public String create() {
		return "addUser";
	}
	
	@PostMapping("/users/add")
	public String store(
			@RequestParam(name="name", defaultValue="")String name,
			@RequestParam(name="email", defaultValue="")String email,
			@RequestParam(name="password", defaultValue="")String password,
			Model model) {

		User user = new User(name, email, password);
		userRepository.save(user);
		
		return "redirect:/users";
	}
	
	
	//＝＝ 更新準備 ＝＝
    @GetMapping("/users/{id}/edit")
    public String edit(
            @PathVariable(name="id")Integer id,
            Model model) {
    	
    	Optional<User> dbDate = userRepository.findById(id);
    	
        if(dbDate.isEmpty()) {
            //商品一覧画面にリダイレクト
            return "redirect:/users";
        }
        
        User user = dbDate.get();
        model.addAttribute("user", user);
    	
    	return "editUser";
    }
    
    //＝＝ 更新 ＝＝
    @PostMapping("/users/{id}/edit")       //パスパラメータ  
    public String update(
            //パスパラメータ 
            @PathVariable(name="id")Integer id,
            //リクエストパラメータ(入力フォーム)
            @RequestParam(name="name", defaultValue="") String name,
            @RequestParam(name="email", defaultValue="") String email,
            @RequestParam(name="password", defaultValue="") String password
            ) {

        //1.
        Optional<User> dbData = userRepository.findById(id);

        //2.
        if(!dbData.isEmpty()) {

            //3.1.
            User user = dbData.get();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            
            //3.2. 更新を実施
            userRepository.save(user);  
        }
        //5.
        return "redirect:/users";
    }
    
    //＝＝ 削除 ＝＝
    
    @PostMapping("/users/{id}/delete")
    public String delete(
            @PathVariable(name="id")Integer id
            ) {

        //2.
        Optional<User> dbData = userRepository.findById(id);

        //3.
        if(!dbData.isEmpty()) {
            userRepository.deleteById(id);
        }

        //4.
        return "redirect:/users";
    }
    
}
