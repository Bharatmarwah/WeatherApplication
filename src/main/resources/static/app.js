const $ = (sel) => document.querySelector(sel);
const cityInput = $('#city');
const getBtn = $('#get');
const resultEl = $('#result');
let inFlight = false;

function renderLoading() {
  resultEl.innerHTML = '<div class="spinner" aria-hidden="true"></div>';
}

function renderError(msg) {
  resultEl.innerHTML = `<div class="error">${msg}</div>`;
}

function renderResult(city, temp) {
  resultEl.innerHTML = `<div>
      <div class=\"city\">${city}</div>
      <div class=\"temp\">${temp} °C</div>
    </div>`;
}

async function fetchWeather() {
  if (inFlight) return;
  const city = cityInput.value.trim();
  if (!city) { renderError('Please enter a city'); return; }

  inFlight = true;
  getBtn.disabled = true;
  renderLoading();
  try {
    const res = await fetch(`/weather/get/${encodeURIComponent(city)}`, { method: 'POST' });
    if (!res.ok) {
      if (res.status === 404) throw new Error('City not found');
      throw new Error(`Server error (${res.status})`);
    }
    const data = await res.json();
    if (!data || typeof data.temperature === 'undefined') throw new Error('Invalid response');
    renderResult(data.city || city, data.temperature);
  } catch (err) {
    renderError(err.message || 'Unknown error');
  } finally {
    inFlight = false;
    getBtn.disabled = false;
  }
}

getBtn.addEventListener('click', fetchWeather);
cityInput.addEventListener('keydown', (e) => { if (e.key === 'Enter') fetchWeather(); });

// small UX: focus input on load
window.addEventListener('load', () => cityInput.focus());
