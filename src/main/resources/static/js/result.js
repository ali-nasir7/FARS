let currentPage = 1;
let pageSize = parseInt(document.getElementById('pageSizeSelect').value);
let searchTerm = "";

const filters = JSON.parse(sessionStorage.getItem("filters") || "{}");

const formatDateTime = dt => {
  if (!dt) return "";
  const d = new Date(dt);
  return isNaN(d) ? dt : d.toLocaleDateString("en-GB") + " " + d.toLocaleTimeString("en-GB", {
    hour: '2-digit',
    minute: '2-digit'
  });
};

async function loadPage(page = 1) {
  try {
    const params = new URLSearchParams();
    for (const k in filters) if (filters[k]) params.append(k, filters[k]);
    params.append("page", page - 1); 
    params.append("size", pageSize);

    const res = await fetch(`/api/activities/search?${params.toString()}`);
    const result = await res.json();

  
    allData = result.activities.map(r => ({
      id: r.faId ?? "",
      unit: r.unit ?? "",
      region: r.region ?? "",
      zone: r.zones ?? "",
      subzone: r.subzone ?? "",
      area: r.areaDescr ?? "",
      faType: r.faType ?? "",
      faStatus: r.faStatus ?? "",
      schedDttm: formatDateTime(r.schedDttm),
      foWorkedDttm: formatDateTime(r.foWorkedDttm),
      name: r.name ?? "",
      address: r.fullAddress ?? "",
      meterNo: r.meterNo ?? "",
      remarks: r.remarks ?? "",
      reason: r.reason ?? "",
      route: r.bookRoute ?? ""
    }));

    renderTablePage(page, result.totalItems);
  } catch (e) {
    console.error(e);
    alert("Error loading data!");
  }
}

function renderTablePage(page, totalCount) {
  const tbody = document.querySelector('#resultsTable tbody');
  tbody.innerHTML = '';

  let filtered = allData.filter(r =>
    Object.values(r).some(v => v.toString().toLowerCase().includes(searchTerm.toLowerCase()))
  );

  if (!filtered.length) {
    tbody.innerHTML = "<tr><td colspan='16' style='text-align:center;color:var(--accent1);padding:20px;'>No data found</td></tr>";
  } else {
    filtered.forEach(r => {
      const tr = document.createElement('tr');
      const fields = ["id", "unit", "region", "zone", "subzone", "area", "faType", "faStatus", "schedDttm", "foWorkedDttm", "name", "address", "meterNo", "remarks", "reason", "route"];
      fields.forEach(f => {
        const td = document.createElement('td');
        td.textContent = r[f] ?? "";
        tr.appendChild(td);
      });
      tbody.appendChild(tr);
    });
  }

  document.getElementById("totalCount").textContent = totalCount;
  document.getElementById("pageCount").textContent = filtered.length;
  renderPagination(totalCount, page);
}

function renderPagination(total, current) {
  const pageCount = Math.ceil(total / pageSize) || 1;
  const pagination = document.getElementById('pagination');
  pagination.innerHTML = '';

  const maxVisible = 10;

  // find current window
  const windowStart = Math.floor((current - 1) / maxVisible) * maxVisible + 1;
  const windowEnd = Math.min(windowStart + maxVisible - 1, pageCount);

  // Prev group button
  if (windowStart > 1) {
    const prevGroup = document.createElement('button');
    prevGroup.textContent = "Prev";
    prevGroup.onclick = () => { currentPage = windowStart - 1; loadPage(currentPage); };
    pagination.appendChild(prevGroup);
  }

  // Page buttons in current window
  for (let i = windowStart; i <= windowEnd; i++) {
    const b = document.createElement('button');
    b.textContent = i;
    if (i === current) b.classList.add('active');
    b.onclick = () => { currentPage = i; loadPage(i); };
    pagination.appendChild(b);
  }

  // Next group button
  if (windowEnd < pageCount) {
    const nextGroup = document.createElement('button');
    nextGroup.textContent = "Next";
    nextGroup.onclick = () => { currentPage = windowEnd + 1; loadPage(currentPage); };
    pagination.appendChild(nextGroup);
  }
}


//  CSV download 
document.getElementById('downloadCsv').addEventListener('click', async () => {
  try {
    const params = new URLSearchParams();
    for (const k in filters) if (filters[k]) params.append(k, filters[k]);
    params.append("page", 0);
    params.append("size", 1000000); // large size to fetch all filtered records

    const res = await fetch(`/api/activities/search?${params.toString()}`);
    if (!res.ok) throw new Error("Failed to fetch data for CSV");

    const result = await res.json();

    if (!result.activities || !result.activities.length) {
      alert("No data to download!");
      return;
    }

    // Normalize full dataset
    const allDownloadData = result.activities.map(r => ({
      id: r.faId ?? "",
      unit: r.unit ?? "",
      region: r.region ?? "",
      zone: r.zones ?? "",
      subzone: r.subzone ?? "",
      area: r.areaDescr ?? "",
      faType: r.faType ?? "",
      faStatus: r.faStatus ?? "",
      schedDttm: formatDateTime(r.schedDttm),
      foWorkedDttm: formatDateTime(r.foWorkedDttm),
      name: r.name ?? "",
      address: r.fullAddress ?? "",
      meterNo: r.meterNo ?? "",
      remarks: r.remarks ?? "",
      reason: r.reason ?? "",
      route: r.bookRoute ?? ""
    }));

    // Convert to CSV
    const headers = Object.keys(allDownloadData[0]);
    let csv = headers.join(",") + "\n";
    allDownloadData.forEach(r => {
      csv += headers
        .map(h => `"${(r[h] ?? '').toString().replace(/"/g, '""')}"`)
        .join(",") + "\n";
    });

    // Trigger download
    const blob = new Blob([csv], { type: 'text/csv' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'SSGC_Report.csv';
    link.click();

  } catch (e) {
    console.error(e);
    alert("Error downloading CSV!");
  }
});

document.getElementById('pageSizeSelect').addEventListener('change', e => {
  pageSize = parseInt(e.target.value);
  currentPage = 1;
  loadPage(currentPage);
});

document.getElementById('searchBox').addEventListener('input', e => {
  searchTerm = e.target.value;
  currentPage = 1;
  renderTablePage(currentPage, allData.length);
});

document.getElementById('backBtn').addEventListener('click', () => {
  window.location.href = '/selection';
});

const colSlider = document.getElementById('colWidth');
const colVal = document.getElementById('colWidthVal');
colSlider.addEventListener('input', (e) => {
  const v = e.target.value;
  document.documentElement.style.setProperty('--col-min', v + 'px');
  colVal.textContent = v + 'px';
});

loadPage(currentPage);
