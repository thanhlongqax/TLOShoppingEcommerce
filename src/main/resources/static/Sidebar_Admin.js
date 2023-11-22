const sidebar = document.querySelector('#sidebar');

// Lấy vị trí ban đầu của sidebar
const initialSidebarTop = sidebar.getBoundingClientRect().top;

// Sử dụng sự kiện cuộn
window.addEventListener('scroll', () => {
  // Lấy vị trí cuộn hiện tại
  const scrollY = window.scrollY;

  // Điều chỉnh vị trí của sidebar
  sidebar.style.top = (initialSidebarTop - scrollY) + 'px';
});