import { Skill } from './skill';

export interface JobCandidate {
    id: number;
    fullName: string;
    contactNumber: string;
    dateOfBirth: string;
    email: string;
    skillList: Skill[];
}
