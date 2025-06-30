package model;

public class Task {
	
	private String taskId;
	private String name;
	private String taskDescription;
	
	public Task(String taskId, String name, String taskDescription) {
			
			//validate inputs against requirements
			boolean isValid = validateInput(taskId, 10);
				
			if(isValid) {
				this.taskId = taskId;
			}
			
			isValid = isValid && setName(name);
			isValid = isValid && setTaskDescription(taskDescription);
			
			if(!isValid) {
				throw new IllegalArgumentException("Invalid input");
			}
			
		}
	
	public boolean setName(String name) {
		boolean isValid = validateInput(name, 20);
		
		if(isValid) {
			this.name = name;
		}
		return isValid;
	}
	
	public boolean setTaskDescription(String taskDescription) {
		boolean isValid = validateInput(taskDescription, 50);
		
		if(isValid) {
			this.taskDescription = taskDescription;
		}
		return isValid;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	
	private boolean validateInput(String item, int length) {
		return (item != null && item.length() <= length);
	}
}
