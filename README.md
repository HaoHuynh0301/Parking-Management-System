# Carparking-Project-2

- Model sử dụng url: https://github.com/sarxos/webcam-capture
- Model nhận diện biển số xe URL: 

- Cơ sở dữ liệu sẽ gồm 4 bảng: 
+ card: dùng để lưu dữ liệu cửa thẻ giữ xe, gồm 2 trường là id và status
+ customer: dùng để lưu thông tin chi tiết của người dùng đăng ký thẻ giữ xe
+ date_time: dùng để lưu lại thông tin ngày hoạt động của phần mềm, để tạo ra folder
             dựa trên ngày hiện tại
+ parking_datetime: dùng để lưu lại thời gian gửi và lấy xe mỗi lần của mỗi người

- Chức năng tạo thẻ xe: Sau khi nhập đầy đủ thông tin, hệ thống sẽ tạo một
                        mã số thẻ, và đồng thời tạo một folder để chứa hình
                        ảnh của người đăng ký

- Chức năng gửi xe: Sau khi hệ thống xác nhận mã số xe người nhập vào đúng,
                    hệ thống sẽ tiếp tục dò trong bảng date_time xe có sự tồn
                    tại của ngày hôm nay chưa, nếu chưa thì hệ thống sẽ tự tạo 
                    folder trong folder của người gửi để lưu lại những hình ảnh
                    lúc gửi xe trong ngày hôm đó. Hình ảnh được gửi sẽ được lưu
                    với định dạng tên là ngày cộng với giờ phút giầy _in để phân 
                    biệt. Đồng thời hệ thống sẽ cập nhật lại trường newest_date trong
                    bảng customer để có thể quản lý thời giản gửi gần nhất, và đồng 
                    thời hệ thống sẽ chèn vào bảng parking_datetime thời gian id và status
                    để tiện sau này có thể in ra được toàn bộ thông tin gửi và lấy xe
                    
- Chức năng lấy xe: Sau khi hệ thống xác nhận mã số người nhập vào đúng, 
                    hệ thống sẽ lấy newest_date trong bảng customer với mã
                    ID do người dùng nhập vào, sau đó lấy ảnh với định dạng tên như trên,
                    sau đó lưu lại hình ảnh đó với thời gian hiện tại _out. Hệ thống
                    cũng sẽ chèn thêm vào bảng parking_datetime
                    
- Chức năng quản lý: Nếu muốn truy cập vào chức năng này bắt buộc phải đăng
                     nhập với quyền admin với username: admin và mật khẩu là
                     admin. Sau đó sẽ có quyền truy cập vào danh sách gửi và lấy xe
                     được trích xuất từ bảng parking_datetime. Có thể xóa thẻ nếu 
                     thấy người dùng lâu không còn hoạt động. Ngoài ra còn có chức năng
                     trích xuất dữ liệu gửi nhận ra file dạng txt để có thể đối chiếu 
                     nếu có việc không hay xảy ra