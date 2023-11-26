
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
// Sử dụng Fetch API để gửi yêu cầu GET đến API
var jsonData = {};
const productContainer = document.getElementById("product-container");
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
            <a onclick="viewProductDetail(${product.id})" class="fas fa-eye"></a>
        </div>
        <div class="image">
            <img src="/api/images/${product.picture}" alt="">
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
    })
    .catch(error => console.error('Error:', error));

// ______________________________________________Chức Năng Xem Chi tiet San Pham____________________________________________________
function viewProductDetail(id) {
  fetch(`http://localhost:8080/api/products/${id}`)
      .then(response => response.json())
      .then(productDetails => {
        // Xử lý thông tin chi tiết sản phẩm nhận được từ API
        // Chuyển hướng người dùng đến trang chi tiết sản phẩm hoặc thực hiện xử lý khác
        window.location.href = `/Detail?id=${id}`;
      })
      .catch(error => console.error('Error:', error));
}
// ______________________________________________End___________________________________________________

// ______________________________________________Chức Năng Giỏ Hàng____________________________________________________

document.getElementById("showCart").style.display = "none";
//Xu li gio hang
let gioHang = [];
function addCart(product, productId) {
  const productDiv = product.closest(".box");
  const hinhSp = (productDiv.querySelector(".image img").src + "").split("/").pop();
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
    quantity :1,
    brand: brandSp,
    color: colorSp,
  };
  gioHang = JSON.parse(sessionStorage.getItem("gioHang")) || [];

  // Check if the product is already in the cart
  const existingProductIndex = gioHang.findIndex((item) => item.id === productId);

  if (existingProductIndex !== -1) {
      gioHang[existingProductIndex].quantity++;
  } else {
    gioHang = [...gioHang, Sp];
  }
  // Save the updated cart back to sessionStorage
  sessionStorage.setItem("gioHang", JSON.stringify(gioHang));
  showCart()
}
showMyCart();
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
        '<img src="/api/images/' + item.picture + ' "  style="width: 100px; height: 100px;">' +
        '<p>' + item.name + '</p>' +
        '</div>' +
        '</td>' +
        '<td> ' + item.quantity+' </td>' +
        '<td>' + item.price * item.quantity + ' .000 VND</td>' +
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
// ___________________________________________________________________________________
//_______________loc san pham__________________________________________________
function filterProducts() {
  const productName = document.getElementById('productName').value;
  var selectBoxColor = document.getElementById("colorDropdown");
  var selectedOptionColor = selectBoxColor.options[selectBoxColor.selectedIndex];
  var color = selectedOptionColor.text;

  var selectBoxCategory = document.getElementById("categoryDropdown");
  var selectedOptionCategory = selectBoxCategory.options[selectBoxCategory.selectedIndex];
  var category = selectedOptionCategory.text;

  var selectBoxBrand = document.getElementById("brandDropdown");
  var selectedOptionBrand = selectBoxBrand.options[selectBoxBrand.selectedIndex];
  var brand = selectedOptionBrand.text;

  const minPrice = document.getElementById('minPrice').value;
  const maxPrice = document.getElementById('maxPrice').value;
  const queryParams = new URLSearchParams();

  if (productName) {
    queryParams.append('productName', productName);
  }

  if (color) {
    queryParams.append('color', color);
  }

  if (category) {
    queryParams.append('category', category);
  }

  if (brand) {
    queryParams.append('brand', brand);
  }

  if (minPrice) {
    queryParams.append('minPrice', minPrice);
  }

  if (maxPrice) {
    queryParams.append('maxPrice', maxPrice);
  }

  fetch(`http://localhost:8080/api/products/filter?${queryParams}`)
      .then(response => response.json())
      .then(filteredProducts => {
        // Process or display the filtered products as needed
        productContainer.innerHTML = ""
        jsonData = filteredProducts
        filteredProducts.forEach(product => { // Lặp qua từng sản phẩm trong mảng JSON và tạo HTML cho từng sản phẩm
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
            <img src="/api/images/${product.picture}" alt="">
        </div>
        <div class="content">
            <h3>${product.name}</h3>
            <div class="price">
                <div class="amount">${product.price} .000 VND</div>
            </div>
            <p>${product.description}</p>
        </div>
    `;
          productContainer.appendChild(productDiv)

        });
      }).catch(error => console.error('Error:', error));
}
//_________________________________________________________________________


//_________________api fetch categories___________________________________
const categoryDropdown = document.getElementById("categoryDropdown");
fetch('http://localhost:8080/api/categories')
    .then(response => response.json())
    .then(categories => {
      categories.forEach(category => {
        const option = document.createElement("option");
        option.value = category.id;
        option.text = category.name;
        categoryDropdown.appendChild(option);
      });
    })
    .catch(error => console.error('Error fetching categories:', error));

//____________________________________________________________________

//_________________api fetch brand___________________________________

const brandDropdown = document.getElementById("brandDropdown");

fetch('http://localhost:8080/api/brands')
    .then(response => response.json())
    .then(brands => {
      brands.forEach(brand => {
        const option = document.createElement("option");
        option.value = brand.id;
        option.text = brand.name;
        brandDropdown.appendChild(option);
      });
    })
    .catch(error => console.error('Error fetching brands:', error));


//____________________________________________________________________

//_________________api fetch color___________________________________
const colorDropdown = document.getElementById("colorDropdown");

fetch('http://localhost:8080/api/colors')
    .then(response => response.json())
    .then(colors => {
      colors.forEach(color => {
        const option = document.createElement("option");
        option.value = color.id;
        option.text = color.name;
        colorDropdown.appendChild(option);
      });
    })
    .catch(error => console.error('Error fetching colors:', error));

//____________________________________________________________________
