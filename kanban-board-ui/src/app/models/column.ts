import { Task } from "./task"

export type Column = {
    columnId : string,
    columnTitle : string,
    taskLimit : number | undefined,
    task:Task[]
}