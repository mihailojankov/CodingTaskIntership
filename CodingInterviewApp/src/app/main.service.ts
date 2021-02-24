import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobCandidate } from './Interfaces/job-candidate';
import { Skill } from './Interfaces/skill';

@Injectable({
  providedIn: 'root'
})
export class MainService {

  constructor(private http: HttpClient) { }

  jobCandidateUrl = "http://localhost:8080/jobCandidate";
  skillUrl = "http://localhost:8080/skill";

  //Candidate routes
  getAllCandidates():Observable<JobCandidate[]>{
    return this.http.get<JobCandidate[]>(this.jobCandidateUrl);
  }
  getCandidateById(id:number):Observable<JobCandidate>{
    return this.http.get<JobCandidate>(this.jobCandidateUrl + "/" + id);
  }
  saveCandidate(candidate:JobCandidate):Observable<JobCandidate>{
    return this.http.post<JobCandidate>(this.jobCandidateUrl, candidate);
  }
  updateCandidate(candidate:JobCandidate):Observable<JobCandidate>{
    return this.http.put<JobCandidate>(this.jobCandidateUrl, candidate);
  }
  deleteCandidate(id:number):Observable<JobCandidate>{
    return this.http.delete<JobCandidate>(this.jobCandidateUrl + "/" + id);
  }
  findCandidateByFullName(fullName:string):Observable<JobCandidate[]>{
    return this.http.get<JobCandidate[]>(this.jobCandidateUrl + "/findByFullName");
  }
  findCandidateBySkills(skills:Skill[]):Observable<JobCandidate[]>{
    return this.http.get<JobCandidate[]>(this.skillUrl + "/findBySkills");
  }

  //Skill routes
  getAllSkills():Observable<Skill[]>{
    return this.http.get<Skill[]>(this.skillUrl);
  }
  getSkillById(id:number):Observable<Skill>{
    return this.http.get<Skill>(this.skillUrl + "/" + id);
  }
  saveSkill(skill:Skill):Observable<Skill>{
    return this.http.post<Skill>(this.skillUrl, skill);
  }
  updateSkill(skill:Skill):Observable<Skill>{
    return this.http.put<Skill>(this.skillUrl, skill);
  }
  deleteSkill(id:number):Observable<Skill>{
    return this.http.delete<Skill>(this.skillUrl + "/" + id);
  }

}
