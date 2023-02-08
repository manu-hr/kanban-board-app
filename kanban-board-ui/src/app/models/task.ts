export type Task = {
    taskId:string,
    taskName : string,
    taskDescription : string,
    priority : string,
    assignedDateAndTime : string  ,
    updatedDateAndTime :  string  ,
    deadlineDateAndTime :  string ,
    status : string,
    assignee : string,
    assignedTo: string[] ,
    watcher : string[] 
}