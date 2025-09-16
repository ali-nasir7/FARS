const API_LOCATIONS = "/api/locations";
const API_ACTIVITIES = "/api/activities/types";
const API_STATUS = "/api/activities/statuses";
const labels = { unit:"Unit", region:"Region", zone:"Zone", subzone:"Subzone", area:"Area", activity:"Field Activity", faStatus:"FA Status" };

const fillDropdown = (id, data) => {
    const dd = document.getElementById(id);
    dd.innerHTML = `<option value="">-- Select ${labels[id] || id} --</option>`;
    if(Array.isArray(data)) data.forEach(v => dd.innerHTML += `<option value="${v}">${v}</option>`);
};
const clearDropdowns = (ids) => ids.forEach(id => document.getElementById(id).innerHTML = `<option value="">-- Select ${labels[id] || id} --</option>`);
const showMessage = (txt, error=false) => {
    const m = document.getElementById("message");
    m.textContent = txt;
    m.style.color = error ? "red" : "#333";
};

async function loadUnits() {
    try {
        const res = await fetch(`${API_LOCATIONS}/units`);
        fillDropdown('unit', await res.json());
    } catch(e) { console.error(e); showMessage("Error loading units", true); }
}
loadUnits();

fetch(API_ACTIVITIES)
  .then(res => res.json())
  .then(data => fillDropdown('activity', data))
  .catch(e => { console.error(e); showMessage("Error loading activities", true); });

fetch(API_STATUS)
  .then(res => res.json())
  .then(data => fillDropdown('faStatus', data))
  .catch(e => { console.error(e); showMessage("Error loading FA Status", true); });

document.getElementById('unit').addEventListener('change', async () => {
    const unit = document.getElementById('unit').value.trim();
    clearDropdowns(['region','zone','subzone','area']);
    if(!unit) return;
    const res = await fetch(`${API_LOCATIONS}/regions/${encodeURIComponent(unit)}`);
    fillDropdown('region', await res.json());
});

document.getElementById('region').addEventListener('change', async () => {
    const region = document.getElementById('region').value.trim();
    clearDropdowns(['zone','subzone','area']);
    if(!region) return;
    const res = await fetch(`${API_LOCATIONS}/zones/${encodeURIComponent(region)}`);
    fillDropdown('zone', await res.json());
});

document.getElementById('zone').addEventListener('change', async () => {
    const zone = document.getElementById('zone').value.trim();
    clearDropdowns(['subzone','area']);
    if(!zone) return;
    const res = await fetch(`${API_LOCATIONS}/subzones/${encodeURIComponent(zone)}`);
    fillDropdown('subzone', await res.json());
});

document.getElementById('subzone').addEventListener('change', async () => {
    const subzone = document.getElementById('subzone').value.trim();
    clearDropdowns(['area']);
    if(!subzone) return;
    const res = await fetch(`${API_LOCATIONS}/areas/${encodeURIComponent(subzone)}`);
    fillDropdown('area', await res.json());
});

const toDateTimeString = (d, end=false) => d ? `${d}T${end ? '23:59:59' : '00:00:00'}` : null;

async function lookupDetails() {
    const btn = document.getElementById("lookupBtn");
    btn.disabled = true;
    showMessage('Loading...');
    const filters = {
        accountId: document.getElementById('accountId').value || null,
        unit: document.getElementById('unit').value || null,
        region: document.getElementById('region').value || null,
        zone: document.getElementById('zone').value || null,
        subzone: document.getElementById('subzone').value || null,
        area: document.getElementById('area').value || null,
        faType: document.getElementById('activity').value || null,
        faStatus: document.getElementById('faStatus').value || null,
        startDate: toDateTimeString(document.getElementById('dateFrom').value),
        endDate: toDateTimeString(document.getElementById('dateTo').value, true)
    };
    try {
        // save filters lightweight
        sessionStorage.setItem("filters", JSON.stringify(filters));
        window.location.href = "/result";
    } catch(e) { console.error(e); showMessage("Error preparing results", true); }
    finally { btn.disabled = false; }
}

/* Logout function */
function logout() {
  window.location.href = "/login";
}
