package se.sundsvall.notes.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.notes.api.model.FindNotesRequest;
import se.sundsvall.notes.integration.db.model.NoteEntity;

import javax.transaction.Transactional;

import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withCaseId;
import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withClientId;
import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withContext;
import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withMunicipalityId;
import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withPartyId;
import static se.sundsvall.notes.integration.db.specification.NoteSpecification.withRole;

@Transactional
@CircuitBreaker(name = "NoteRepository")
public interface NoteRepository extends JpaRepository<NoteEntity, String>, JpaSpecificationExecutor<NoteEntity> {

	default Page<NoteEntity> findAllByParameters(String municipalityId, FindNotesRequest findNotesRequest, Pageable pageable) {
		return this.findAll(withPartyId(findNotesRequest.getPartyId())
			.and(withCaseId(findNotesRequest.getCaseId()))
			.and(withContext(findNotesRequest.getContext())
			.and(withRole(findNotesRequest.getRole()))
			.and(withMunicipalityId(municipalityId))
			.and(withClientId(findNotesRequest.getClientId()))), pageable);
	}

	boolean existsByIdAndMunicipalityId(String id, String municipalityId);
}
