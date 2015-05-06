package org.sss.blogger.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.sss.blogger.entity.Blog;
import org.sss.blogger.entity.Item;
import org.sss.blogger.entity.Role;
import org.sss.blogger.entity.User;
import org.sss.blogger.repository.BlogRepository;
import org.sss.blogger.repository.ItemRepository;
import org.sss.blogger.repository.RoleRepository;
import org.sss.blogger.repository.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userReposotory;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> findAll(){
		return userReposotory.findAll();
	}

	public User findOne(int id) {
		return userReposotory.findOne(id);
	}

	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);
		for(Blog blog : blogs){
			List<Item> items = itemRepository.findByBlog(blog, new PageRequest(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		user.setBlogs(blogs);
		return user;
	}

	public void save(User user) {
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		
		userReposotory.save(user);
	}

	public User findOneWithBlogs(String name) {
		User user = userReposotory.findByName(name);
		return findOneWithBlogs(user.getId());
	}

	public void delete(int id) {
		userReposotory.delete(id);
	}

}
