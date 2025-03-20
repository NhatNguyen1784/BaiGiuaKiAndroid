package vn.hcmute.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hcmute.entity.User;
import vn.hcmute.repository.UserRepository;
import vn.hcmute.services.AuthService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public String Login(@RequestBody User user) {
        return authService.Login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/users")
    public List<User> Users(){
        List<User> u = authService.users();
        System.out.println(u.size());
        return u;
    }

    @PostMapping("/sendcode")
    public String SendOtp(@RequestParam String email) {
        return authService.SendOtp(email);
    }

    @PostMapping("/register")
    public String Register(@RequestBody User newUser, @RequestParam String otp) {
        // Kiểm tra OTP người dùng nhập vào
        if (!authService.VerifyOtp(newUser.getEmail(), otp)) {
            return "Invalid OTP";  // Trả về thông báo nếu OTP không chính xác
        }

        // Kiểm tra xem email đã tồn tại chưa
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return "Email already exists";  // Nếu email đã tồn tại
        }

        // Lưu người dùng mới vào cơ sở dữ liệu
        userRepository.save(newUser);
        authService.otpCache.remove(newUser.getEmail());
        return "User registered successfully";  // Trả về thông báo đăng ký thành công
    }

    @PostMapping("/forgotPassword")
    public String ForgotPassword(@RequestParam String email, @RequestParam String pass, @RequestParam String otp) {
        if(!userRepository.findByEmail(email).isPresent()) {
            return "Email not found";
        }
        // Kiểm tra OTP người dùng nhập vào
        if (!authService.VerifyOtp(email, otp)) {
            return "Invalid OTP";  // Trả về thông báo nếu OTP không chính xác
        }
        User u = userRepository.findUserByEmail(email);
        u.setPassword(pass);
        userRepository.save(u);
        authService.otpCache.remove(email);
        return "User forgot password successfully";
    }
}
