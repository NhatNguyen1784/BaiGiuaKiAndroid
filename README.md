- Vào MySQL, thực hiện gõ 3 dòng này tạo tài khoản, database và cấp quyền cho user:
CREATE database api default character set utf8; 
CREATE user 'api'@'%.%.%.%' identified by 'api!'; 
GRANT ALL PRIVILEGES ON api.* TO 'api'@'%.%.%.%';
- Sau đó thực hiện vào Network/Retrofit đổi ip thành localhost nếu như tự chạy, hoặc đổi thành ip nếu như máy khác bật API.

Câu 1: 22110405 Lê Đào Nhân Sâm
Câu 2: 22110295 Đặng Đăng Duy
Câu 3: 22110307 Nguyễn Duy Đạt
Câu 4: Cả Nhóm
Câu 5: 22110450 Võ Văn Tuấn
Câu 6: 21110912 Nguyễn Hữu Phong
Câu 7: 22110383 Dương Khánh Nguyên (API Đăng kí, Đăng nhập), 22110383 Nguyễn Nhật Nguyên (API full category, last product)
