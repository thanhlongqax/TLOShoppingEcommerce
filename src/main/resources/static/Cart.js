
// Lấy tham chiếu đến phần tử chứa sản phẩm
const productContainer = document.getElementById("product-container");

// Simulate JSON data (có thể thay thế bằng việc tải dữ liệu từ tệp JSON)
const jsonData = [
    {
        "name": "Sản phẩm 1",
        "price": 20,
        "image": "image/product_img1.jpg",
        "description": "Mô tả sản phẩm 1"
    },
    {
        "name": "Sản phẩm 2",
        "price": 25,
        "image": "image/product_img2.jpg",
        "description": "Mô tả sản phẩm 2"
    },
    {
      "name": "Sản phẩm 3",
      "price": 25,
      "image": "image/product_img2.jpg",
      "description": "Mô tả sản phẩm 2"
  }
    // Thêm dữ liệu sản phẩm khác ở đây
];
// Lấy dữ liệu từ sessionStorage
const gioHang = JSON.parse(sessionStorage.getItem('gioHang'));

// Lấy thẻ tbody trong table để thêm các sản phẩm vào
const tbody = document.querySelector('.site-blocks-table tbody');

// Kiểm tra xem gioHang có dữ liệu hay không
if (gioHang && gioHang.length > 0) {
  // Duyệt qua từng sản phẩm trong gioHang và thêm vào tbody
  gioHang.forEach((item, index) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td class="product-name">
        <h2 class="h5 text-black">${item.name}</h2>
      </td>
      <td class="product-thumbnail">
        <img src="${item.picture}" alt="Image" class="img-fluid">
      </td>
      <td>${item.price}.000 VND</td>
      <td>
        <div class="input-group mb-3" style="max-width: 120px;">
          <div class="input-group-prepend">
            <button class="btn btn-outline-primary js-btn-minus" type="button">&minus;</button>
          </div>
          <input type="text" class="form-control text-center" value="${item.quality}" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1">
          <div class="input-group-append">
            <button class="btn btn-outline-primary js-btn-plus" type="button">&plus;</button>
          </div>
        </div>
      </td>
      <td>${item.price * item.quality}.000 VND</td>
      <td><a href="#" class="btn btn-primary btn-sm" onclick="removeItem(${index})">X</a></td>
    `;
    // Thêm hàng vào tbody
    tbody.appendChild(row);
  });
} else {
  // Nếu gioHang trống, hiển thị thông báo
  const emptyRow = document.createElement('tr');
  emptyRow.innerHTML = '<td colspan="6">Giỏ Hàng Trống</td>';
  tbody.appendChild(emptyRow);
}

// Hàm để xóa sản phẩm khỏi gioHang
function removeItem(index) {
  gioHang.splice(index, 1);
  sessionStorage.setItem('gioHang', JSON.stringify(gioHang));
  // Sau khi xóa, cập nhật lại trang
  location.reload();
}

// Lấy tất cả các nút "±"
const btnMinusList = document.querySelectorAll('.js-btn-minus');
const btnPlusList = document.querySelectorAll('.js-btn-plus');
// Sử dụng forEach để thêm sự kiện nghe vào từng nút "±"
btnMinusList.forEach((btnMinus, index) => {
    btnMinus.addEventListener('click', () => {
        updateQuantity(index, -1);
    });
});

btnPlusList.forEach((btnPlus, index) => {
    btnPlus.addEventListener('click', () => {
        updateQuantity(index, 1);
    });
});

// Hàm cập nhật số lượng và giá tiền
function updateQuantity(index, change) {
    // Đảm bảo sản phẩm tồn tại trong giỏ hàng
    if (gioHang[index]) {
        gioHang[index].quality += change;

        // Đảm bảo số lượng không dưới 1
        if (gioHang[index].quality < 1) {
            gioHang[index].quality = 1;
        }

        // Lưu lại giỏ hàng vào sessionStorage
        sessionStorage.setItem('gioHang', JSON.stringify(gioHang));

        // Cập nhật giá trị của input tương ứng
        const inputElement = document.querySelector(`#quantity-input-${index}`);
        if (inputElement) {
            inputElement.value = gioHang[index].quality;
        }
        location.reload();
    }

}
function updateCartSummary() {
    const cartSummary = document.getElementById('cartSummary');

    // Lấy giỏ hàng từ sessionStorage
    const gioHang = JSON.parse(sessionStorage.getItem('gioHang')) || [];

    // Tính toán tổng giá tiền và số lượng
    let subtotal = 0;
    let totalItems = 0;

    gioHang.forEach(item => {
        subtotal += item.price * item.quality;
        totalItems += item.quality;
    });

    // Tạo HTML để cập nhật trạng thái giỏ hàng
    const htmlContent = `
    <div class="row justify-content-end">
      <div class="col-md-7">
        <div class="row">
          <div class="col-md-12 text-right border-bottom mb-5">
            <h3 class="text-black h4 text-uppercase">Cart Totals</h3>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <span class="text-black">Total Items</span>
          </div>
          <div class="col-md-6 text-right">
            <strong class="text-black">${totalItems}</strong>
          </div>
        </div>
        <div class="row mb-5">
          <div class="col-md-6">
            <span class="text-black">ToTal</span>
          </div>
          <div class="col-md-6 text-right">
            <strong class="text-black"> ${subtotal}.000 VND</strong>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <button class="btn btn-primary btn-lg py-3 btn-block" onclick="saveCartToDatabase()">Proceed To Checkout</button>
          </div>
        </div>
      </div>
    </div>
  `;

    // Cập nhật nội dung của cartSummary
    cartSummary.innerHTML = htmlContent;
}

// Gọi hàm cập nhật khi trang tải
updateCartSummary();
function saveCartToDatabase() {
  //Gửi yêu cầu POST đến API để lưu giỏ hàng vào CSDL
  fetch('/api/cart/save', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: sessionStorage.getItem('gioHang'),
  })
      .then(response => response.json())
      .then(data => {
        console.log('Success:', data);
        // Xóa giỏ hàng sau khi đã lưu vào CSDL nếu cần
        sessionStorage.removeItem('gioHang');
      })
      .catch((error) => {
        console.error('Error:', error);
      });
}


// ___________________________________________________________________________________