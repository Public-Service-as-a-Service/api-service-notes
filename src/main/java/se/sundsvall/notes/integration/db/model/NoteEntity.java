package se.sundsvall.notes.integration.db.model;

import static java.time.OffsetDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "note",
	indexes = {
		@Index(name = "note_party_id_index", columnList = "party_id"),
		@Index(name = "note_context_index", columnList = "context"),
		@Index(name = "note_client_id_index", columnList = "client_id"),
		@Index(name = "note_role_index", columnList = "role"),
	})
public class NoteEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private String id;

	@Column(name = "party_id")
	private String partyId;

	@Column(name = "context")
	private String context;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "role")
	private String role;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created")
	private OffsetDateTime created;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified")
	private OffsetDateTime modified;

	@Column(name = "subject")
	private String subject;

	@Column(name = "body")
	@Lob
	private String body;

	@Column(name = "case_id")
	private String caseId;

	@Column(name = "case_type")
	private String caseType;

	@Column(name = "case_link", length = 512)
	private String caseLink;

	@Column(name = "external_case_id")
	private String externalCaseId;

	public static NoteEntity create() {
		return new NoteEntity();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NoteEntity withId(final String id) {
		this.id = id;
		return this;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(final String partyId) {
		this.partyId = partyId;
	}

	public NoteEntity withPartyId(final String partyId) {
		this.partyId = partyId;
		return this;
	}

	public String getContext() {
		return context;
	}

	public void setContext(final String context) {
		this.context = context;
	}

	public NoteEntity withContext(final String context) {
		this.context = context;
		return this;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public NoteEntity withClientId(final String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public NoteEntity withRole(final String role) {
		this.role = role;
		return this;
	}

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(final OffsetDateTime created) {
		this.created = created;
	}

	public NoteEntity withCreated(final OffsetDateTime created) {
		this.created = created;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public NoteEntity withCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public OffsetDateTime getModified() {
		return modified;
	}

	public void setModified(final OffsetDateTime modified) {
		this.modified = modified;
	}

	public NoteEntity withModified(final OffsetDateTime modified) {
		this.modified = modified;
		return this;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public NoteEntity withModifiedBy(final String modifiedBy) {
		this.modifiedBy = modifiedBy;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public NoteEntity withSubject(final String subject) {
		this.subject = subject;
		return this;
	}

	public String getBody() {
		return body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	public NoteEntity withBody(final String body) {
		this.body = body;
		return this;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(final String caseId) {
		this.caseId = caseId;
	}

	public NoteEntity withCaseId(final String caseId) {
		this.caseId = caseId;
		return this;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(final String caseType) {
		this.caseType = caseType;
	}

	public NoteEntity withCaseType(final String caseType) {
		this.caseType = caseType;
		return this;
	}

	public String getCaseLink() {
		return caseLink;
	}

	public void setCaseLink(final String caseLink) {
		this.caseLink = caseLink;
	}

	public NoteEntity withCaseLink(final String caseLink) {
		this.caseLink = caseLink;
		return this;
	}

	public String getExternalCaseId() {
		return externalCaseId;
	}

	public void setExternalCaseId(final String externalCaseId) {
		this.externalCaseId = externalCaseId;
	}

	public NoteEntity withExternalCaseId(final String externalCaseId) {
		this.externalCaseId = externalCaseId;
		return this;
	}

	@PrePersist
	void prePersist() {
		created = now().truncatedTo(MILLIS);
	}

	@PreUpdate
	void preUpdate() {
		modified = now().truncatedTo(MILLIS);
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, caseId, caseLink, caseType, clientId, context, created, createdBy, externalCaseId, id, modified, modifiedBy, partyId, role, subject);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		final var other = (NoteEntity) obj;
		return Objects.equals(body, other.body) && Objects.equals(caseId, other.caseId) && Objects.equals(caseLink, other.caseLink) && Objects.equals(caseType, other.caseType) && Objects.equals(clientId, other.clientId) && Objects.equals(context,
			other.context) && Objects.equals(created, other.created) && Objects.equals(createdBy, other.createdBy) && Objects.equals(externalCaseId, other.externalCaseId) && Objects.equals(id, other.id) && Objects.equals(modified, other.modified)
			&& Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(partyId, other.partyId) && Objects.equals(role, other.role) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		final var builder = new StringBuilder();
		builder.append("NoteEntity [id=").append(id).append(", partyId=").append(partyId).append(", context=").append(context).append(", clientId=").append(clientId).append(", role=").append(role).append(", createdBy=").append(createdBy).append(
			", created=").append(created).append(", modifiedBy=").append(modifiedBy).append(", modified=").append(modified).append(", subject=").append(subject).append(", body=").append(body).append(", caseId=").append(caseId).append(", caseType=").append(
				caseType).append(", caseLink=").append(caseLink).append(", externalCaseId=").append(externalCaseId).append("]");
		return builder.toString();
	}
}
