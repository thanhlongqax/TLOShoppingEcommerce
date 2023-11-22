
let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () => {
  searchForm.classList.toggle('active');
  navbar.classList.remove('active');
}

let navbar = document.querySelector('.navbar');

document.querySelector('#menu-btn').onclick = () => {
  navbar.classList.toggle('active');
  searchForm.classList.remove('active');
}
window.onscroll = () => {
  searchForm.classList.remove('active');
  navbar.classList.remove('active');
}
let filterBtn = document.querySelectorAll('.filter-buttons .buttons');
let filterItem = document.querySelectorAll('.products .box-container .box');

filterBtn.forEach(button => {

  button.onclick = () => {
    filterBtn.forEach(btn => btn.classList.remove('active'));
    button.classList.add('active');

    let dataFilter = button.getAttribute('data-filter');

    filterItem.forEach(item => {

      item.classList.remove('active');
      item.classList.add('hide');

      if (item.getAttribute('data-item') == dataFilter || dataFilter == 'all') {
        item.classList.remove('hide');
        item.classList.add('active');
      }

    });

  };

});

var swiper = new Swiper(".home-slider", {
  centeredSlides: true,
  loop: true,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
});

var swiper = new Swiper(".featured-slider", {
  centeredSlides: true,
  loop: true,
  spaceBetween: 20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    450: {
      slidesPerView: 2,
    },
    768: {
      slidesPerView: 3,
    },
    1200: {
      slidesPerView: 4,
    },
  },
});

var swiper = new Swiper(".review-slide", {
  loop: true,
  spaceBetween: 20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    768: {
      slidesPerView: 2,
    },
  },
});

var swiper = new Swiper(".blogs-slider", {
  centeredSlides: true,
  loop: true,
  spaceBetween: 20,
  autoplay: {
    delay: 9500,
    disableOnInteraction: false,
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  breakpoints: {
    0: {
      slidesPerView: 1,
    },
    768: {
      slidesPerView: 2,
    },
    1200: {
      slidesPerView: 3,
    },
  },
});

//_____________________________________________________________________________________________
// Lấy tham chiếu đến phần tử chứa sản phẩm
const productContainer = document.getElementById("product-container");
// Simulate JSON data (có thể thay thế bằng việc tải dữ liệu từ tệp JSON)
// const data = [
//     {
//         "id " : 1,
//         "name": "Sản phẩm 1",
//       "brand" : "Levins",
//       "color" : "pink",
//         "price": 20,
//         "image": "image/product_img1.jpg",
//         "description": "Mô tả sản phẩm 1"
//     },
//     {
//         "id " : 2,
//         "name": "Sản phẩm 2",
//       "brand" : "Levins",
//       "color" : "pink",
//         "price": 25,
//         "image": "image/product_img2.jpg",
//         "description": "Mô tả sản phẩm 2"
//     },
//     {
//       "id " : 3,
//       "name": "Sản phẩm 3",
//       "brand" : "Levins",
//       "color" : "pink",
//       "price": 25,
//       "image": "image/product_img2.jpg",
//       "description": "Mô tả sản phẩm 2"
//   }
//     // Thêm dữ liệu sản phẩm khác ở đây
// ];
// Sử dụng Fetch API để gửi yêu cầu GET đến API
var jsonData = {};
fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(data => {
      jsonData = data
      data.forEach(product => { // Lặp qua từng sản phẩm trong mảng JSON và tạo HTML cho từng sản phẩm
        const productDiv = document.createElement("div");
        productDiv.className = "box"; // Đặt lớp CSS
        // Tạo nội dung sản phẩm
        productDiv.innerHTML = `
        <div class="icons">
            <a onclick="addCart(this, ${product.id})" class="fas fa-shopping-cart"></a>
            <a href="#" class="fas fa-heart"></a>
            <a href="#" class="fas fa-search"></a>
            <a href="#" class="fas fa-eye"></a>
        </div>
        <div class="image">
            <img th:src="${product.image}" alt="">
        </div>
        <div class="content">
            <h3>${product.name}</h3>
            <div class="price">
                <div class="amount">${product.price} .000 VND</div>
            </div>
            <p>${product.description}</p>
        </div>
    `;

        // Đưa sản phẩm vào vùng chứa
        productContainer.appendChild(productDiv);
      });
      console.log(data);
    })
    .catch(error => console.error('Error:', error));
// ______________________________________________Chức Năng Giỏ Hàng____________________________________________________

document.getElementById("showCart").style.display = "none";
//Xu li gio hang
let gioHang = [];
function addCart(product, productId) {
  const productDiv = product.closest(".box");
  const hinhSp = productDiv.querySelector(".image img").src;
  const tenSp = productDiv.querySelector(".content h3").innerText;
  const giaSp = productDiv.querySelector(".content .amount").innerText;
  const chiTietSp = productDiv.querySelector(".content p").innerText;
  const brandSp = jsonData.find(item => item.id === productId).brand; // Lấy giá trị brand từ dữ liệu JSON
  const colorSp = jsonData.find(item => item.id === productId).color; // Lấy giá trị color từ dữ liệu JSON
  const Sp = {
    id: productId,
    picture: hinhSp,
    name: tenSp,
    price: parseInt(giaSp, 10),
    detailProduct : chiTietSp,
    quality :1,
    brand: brandSp,
    color: colorSp,
  };
  gioHang = JSON.parse(sessionStorage.getItem("gioHang")) || [];

  // Check if the product is already in the cart
  const existingProductIndex = gioHang.findIndex((item) => item.id === productId);

  if (existingProductIndex !== -1) {
      gioHang[existingProductIndex].quality++;
  } else {
    gioHang = [...gioHang, Sp];
  }

  // Save the updated cart back to sessionStorage
  sessionStorage.setItem("gioHang", JSON.stringify(gioHang));
  showMyCart();
}

function showMyCart() {
  gioHang = JSON.parse(sessionStorage.getItem('gioHang'));
  let ttgh = "";
  let count = 1;
  if (gioHang && gioHang.length > 0) {
    gioHang.forEach((item, i) => {
      ttgh +=
        '<tr>' +
        '<td>' +
        '<div class="quant-input">' +
        '<div class="arrows">' +
        '<div class="arrow plus gradient"><span class="ir"><i class="fas fa-sort-asc"></i></span></div>' +
        '<div class="arrow minus gradient"><span class="ir"><i class="fas fa-sort-desc"></i></span></div>' +
        '</div>' +
        count++ +
        '</div>' +
        '</td>' +
        '<td>' +
        '<div class="box">' +
        '<img th:src="' + item.picture + ' " style="width: 100px; height: 100px;">' +
        '<p>' + item.name + '</p>' +
        '</div>' +
        '</td>' +
        '<td> ' + item.quality+' </td>' +
        '<td>' + item.price * item.quality + ' .000 VND</td>' +
        '<td><a href="#" class="fas fa-trash" onclick="deleteCart(' + i + ')"></a></td>' +
        '</tr>';
    });
    ttgh += '<tr><td colspan="5" class="cartButtonContainer"><button onclick="redirectToCart()">Chuyển đến Giỏ Hàng</button></td></tr>';
  } else {
    ttgh = '<tr><td colspan="5">Giỏ Hàng Trống</td></tr>';
  }

  document.getElementById("myCart").innerHTML = ttgh;
}
showMyCart();
// Hàm chuyển hướng đến trang giỏ hàng
function redirectToCart() {
  // Chuyển đến trang giỏ hàng (ví dụ: "cart.html")
  window.location.href = "/Cart";
}
function showCart() {
  let x = document.getElementById("showCart");

  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
  showMyCart();
}
function deleteCart(index) {
  if (index >= 0 && index < gioHang.length) {
    gioHang.splice(index, 1); // Remove the item at the specified index
    // Save the updated cart to sessionStorage
    sessionStorage.setItem("gioHang", JSON.stringify(gioHang));
    // Update the cart display
    showMyCart();
  }
}
function saveCartToDatabase() {
  const cartItems = JSON.parse(sessionStorage.getItem('gioHang'));

  // Gửi yêu cầu POST đến API để lưu giỏ hàng vào CSDL
  fetch('/api/cart/save', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(cartItems),
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