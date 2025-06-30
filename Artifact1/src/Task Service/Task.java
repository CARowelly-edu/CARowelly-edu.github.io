package model;

public class Task {
	
	private String taskId;
	private String name;
	private String description;
	
	public Task(String taskId, String name, String description) {
			
			//validate inputs against requirements
			boolean isValid = validateInput(taskId, 10);
				
			if(isValid) {
				this.taskId = taskId;
			}
			
			isValid = isValid && setName(name);
			isValid = isValid && setDescription(description);
			
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
	
	public boolean setDescription(String description) {
		boolean isValid = validateInput(description, 50);
		
		if(isValid) {
			this.description = description;
		}
		return isValid;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	private boolean validateInput(String item, int length) {
		return (item != null && item.length() <= length);
	}
}
