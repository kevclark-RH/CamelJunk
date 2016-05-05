package com.redhat.cml.CamelJunk;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator=",",crlf="UNIX")
public class Classroom implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9053200993457751508L;
	
	@DataField(pos=1)
	private String Professor;
	@DataField(pos=2)
	private List<String> students;
	@DataField(pos=3)
	private int room_number;
	
	public Classroom(){
		
	}
	
	
	 private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		 out.writeObject(Professor);
		 out.writeObject(students);
		 out.writeInt(room_number);
	 }

	 private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		 Professor = (String) in.readObject();
		 students = (ArrayList<String>) in.readObject();
		 room_number = in.readInt()+10;
	 }
	 
	 
	 private void readObjectNoData() throws ObjectStreamException{
		 throw new InvalidObjectException("Stream data required");
	 }
	 
	 
	/**
	 * @return the professor
	 */
	public String getProfessor() {
		return Professor;
	}

	/**
	 * @param professor the professor to set
	 */
	public void setProfessor(String professor) {
		Professor = professor;
	}

	/**
	 * @return the students
	 */
	public List<String> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(ArrayList<String> students) {
		this.students = students;
	}

	/**
	 * @return the room_number
	 */
	public int getRoom_number() {
		return room_number;
	}

	/**
	 * @param room_number the room_number to set
	 */
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	
	public String toString(){
		return Integer.toString(this.room_number)+":"+this.Professor+"---"+this.students.toString();
	}
	
	
}
