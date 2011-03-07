package skeleton.skull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import skeleton.exception.ResourceNotFound;

/**
 * The data access object for all skulls
 */
@Repository
public class SkullDAO {

	/** The entity manager. */
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	EntityManager entityManager;

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the skull id
	 * @return the skull
	 * @throws ResourceNotFound
	 *             the resource was not found
	 */
	public Skull findById(Integer id) throws ResourceNotFound {
		Skull skull = entityManager.find(Skull.class, id);
		if (skull == null) {
			throw new ResourceNotFound();
		}
		return skull;
	}

	/**
	 * Persists the skull.
	 * 
	 * @param skull
	 *            the skull to persist
	 */
	@Transactional
	public void persist(Skull skull) {
		entityManager.persist(skull);
	}

	/**
	 * Flush the skull to the datasource.
	 * 
	 * @param skull
	 *            skull to flush
	 * @return flushed skull
	 */
	@Transactional
	public Skull flush(Skull skull) {
		entityManager.flush();
		return skull;
	}

	/**
	 * Removes the skull.
	 * 
	 * @param skull
	 *            skull to be deleted
	 */
	public void remove(Skull skull) {
		entityManager.remove(skull);
	}

	/**
	 * Merges the skull. Either an update or an insert.
	 * 
	 * @param skull
	 *            the skull to be merge
	 * @return the merged skull
	 */
	@Transactional
	public Skull merge(Skull skull) {
		return entityManager.merge(skull);
	}

	/**
	 * Refresh the skull with new data from the datasource.
	 * 
	 * @param skull
	 *            the skull to be refreshed
	 */
	public void refresh(Skull skull) {
		entityManager.refresh(skull);
	}
}
