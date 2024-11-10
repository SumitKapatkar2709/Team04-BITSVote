// candidate.model.ts
export class Candidate {
  candidateId: number;
  candidateName: string;
  votes: number;
  electionId?: number; // Add this if you need a reference to the election in the model
}
