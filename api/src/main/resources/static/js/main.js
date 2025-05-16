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
        alert("Task added successfully!");
        loadTasks(userId);
        taskForm.reset();
    } else {
        alert("Error adding task.");
    }
});

async function loadTasks(userId) {
    const response = await fetch(`/users/${userId}/tasks`);
    const tasks = await response.json();

    taskList.innerHTML = '';

    if (tasks.length === 0) {
        taskList.innerHTML = '<div class="alert alert-info">No tasks found.</div>';
        return;
    }

    tasks.forEach((task, index) => {
        const taskItem = document.createElement('div');
        taskItem.className = 'card mb-2 shadow-sm';

        taskItem.innerHTML = `
            <div class="card-body">
                <h5 class="card-title">Tarefa ${index + 1}</h5>
                <p class="card-text">${task.description}</p>
                <span class="badge bg-${task.done ? 'success' : 'secondary'}">
                    ${task.done ? 'Concluída' : 'Pendente'}
                </span>
            </div>
        `;

        taskList.appendChild(taskItem);
    });
}
