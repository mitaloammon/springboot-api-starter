const taskForm = document.getElementById('taskForm');
const taskList = document.getElementById('taskList');

taskForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const userId = document.getElementById('userId').value;
    const description = document.getElementById('taskDesc').value;

    const response = await fetch(`/users/${userId}/tasks`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ description, done: false })
    });

    if (response.ok) {
        alert("Tasks add with successfully!");
        loadTasks(userId);
        taskForm.reset();
    } else {
        alert("Error.");
    }
});

async function loadTasks(userId) {
    const response = await fetch(`/users/${userId}/tasks`);
    const tasks = await response.json();

    taskList.innerHTML = '';
    tasks.forEach((task, index) => {
        const li = document.createElement('li');
        li.textContent = `${index + 1}. ${task.description} - Feita: ${task.done ? "Yes" : "No"}`;
        taskList.appendChild(li);
    });
}
