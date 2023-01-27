package com.project.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name="typeword")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeWord  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeID;
    public long getTypeID() {
		return typeID;
	}
	public void setTypeID(long typeID) {
		this.typeID = typeID;
	}
	@Column(name="nametype", columnDefinition = "nvarchar(40)  null")
    private String nameType;
    @OneToMany(mappedBy = "typeWord", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private Collection<Word> word;
}
