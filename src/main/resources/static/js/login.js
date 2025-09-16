const page = document.documentElement;
const themeToggle = document.getElementById('themeToggle');
const stored = localStorage.getItem('fars_theme') || 
  (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light');

if (stored) page.setAttribute('data-theme', stored);
themeToggle.addEventListener('click', toggleTheme);
themeToggle.addEventListener('keydown', (e)=> {
  if (e.key === 'Enter' || e.key === ' ') {
    e.preventDefault();
    toggleTheme();
  }
});

function toggleTheme() {
  const cur = page.getAttribute('data-theme') === 'dark' ? 'dark' : 'light';
  const next = cur === 'dark' ? 'light' : 'dark';
  page.setAttribute('data-theme', next);
  localStorage.setItem('fars_theme', next);
  themeToggle.setAttribute('aria-pressed', next === 'dark');
}


const pwd = document.getElementById('password');
const toggleBtn = document.getElementById('togglePwd');
toggleBtn.addEventListener('click', ()=> {
  if (pwd.type === 'password') {
    pwd.type = 'text';
    toggleBtn.textContent = 'Hide';
    toggleBtn.setAttribute('aria-label','Hide password');
  } else {
    pwd.type = 'password';
    toggleBtn.textContent = 'Show';
    toggleBtn.setAttribute('aria-label','Show password');
  }
});

window.addEventListener('load', ()=> document.getElementById('username').focus());
