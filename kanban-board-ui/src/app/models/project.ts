import { Column } from "./column"

export type Project = {
    projectId : string,
    projectName : string,
    projectDescription : string,
    deadline : string,
    createdDate : string,
    members : Array<any>,
    columns : Column[]  

}