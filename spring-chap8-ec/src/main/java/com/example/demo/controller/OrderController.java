package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.model.Cart;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

@Controller
public class OrderController {
	
	//処理で触るために、セッションのカートを呼ぶ
	@Autowired
	Cart cart;
	
	
	//処理で触るテーブルに応じたRepositoryを呼ぶ
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository orederDetailRepository;
	
	//カート一覧の注文するボタンを押下した時の処理
	@GetMapping("/order")
	public String index() {
		return "customerForm";
	}

	
	//注文
	@PostMapping("/order/confirm")
	public String confirm(
			@RequestParam(name ="name") String name,
			@RequestParam(name ="address") String address,
			@RequestParam(name ="tel") String tel,
			@RequestParam(name ="email") String email,
			Model model
			) {
		
		model.addAttribute("name", name);
		model.addAttribute("address", address);
		model.addAttribute("tel", tel);
		model.addAttribute("email", email);
		
		return "orderConfirm";
	}
	
	//確認画面で注文確認後
	
	@PostMapping("/order")
	public String ordered(
			@RequestParam(name ="name") String name,
			@RequestParam(name ="address") String address,
			@RequestParam(name ="tel") String tel,
			@RequestParam(name ="email") String email,
			Model model
			) {
		
		
		// * DBの数値の登録上、手順を間違えると機能しない
		
		//1. 顧客情報を登録 
		//2. 注文情報を登録
		//3. 注文詳細情報を登録
		//4. 注文番号を完了画面に渡す
		
		
		//1.
		//受け取ったパラメータを用いて顧客情報のオブジェクト生成
		Customer customer = new Customer(name, address, tel, email);
		//顧客データ登録
		customerRepository.save(customer);

		
		//2.
		//データ作成によって作られたidの取得
		Integer cutomerId = customer.getId();
		Integer totalPrice = cart.getTotalPrice();
		
		//注文情報のオブジェクト生成
		Order order = new Order(cutomerId, LocalDate.now(), totalPrice);
		//注文テーブルにデータを登録
		orderRepository.save(order);
		
		
		//3.
		//order内の商品idの取得
		Integer orderId = order.getId();
		
		//カートの中のitemListを1つずつ取り出して登録する
		for(Item item : cart.getItemList()) {
			Integer itemId = item.getId();
			Integer quantity = item.getQuantity();
			
			// 注文詳細のオブジェクト生成
			OrderDetail orederDetail = new OrderDetail(orderId, itemId, quantity);
			
			// 注文詳細データを登録
			orederDetailRepository.save(orederDetail);
		}
		
		
		//4.
		model.addAttribute("orderId", orderId);
		
		
		
		
//		model.addAttribute("name", name);
//		model.addAttribute("address", address);
//		model.addAttribute("tel", tel);
//		model.addAttribute("email", email);
		
		return "ordered";
	}
}
