package app.dao;

import app.dto.PlantDTO;
import app.dto.RessellerDTO;
import app.entities.Plant;
import app.entities.Reseller;
import app.exceptions.JPAException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class PlantDAO implements IDAO<PlantDTO> {
    private EntityManagerFactory emf;

    public PlantDAO(EntityManagerFactory emf) {
        this.emf = emf;

    }

    @Override
    public List<PlantDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p", Plant.class);
            return PlantDTO.toDTOList(query.getResultList());
        } catch (Exception e) {
            throw new JPAException("ERROR, no list of Plants  was found" + e.getMessage());
        }
    }

    @Override
    public PlantDTO getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Plant plant = em.find(Plant.class, id);
            if (plant == null) {
                throw new EntityNotFoundException("Plant with id " + id + " not found");
            }
            return new PlantDTO(plant);
        } catch (Exception e) {
            throw new JPAException("ERROR, ID not found" + e.getMessage());
        }

    }

    @Override
    public List<PlantDTO> getByType(String type) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p WHERE p.planttype = :type", Plant.class);
            return PlantDTO.toDTOList(query.getResultList());
        } catch (Exception e) {
            throw new JPAException("ERROR, no list of Plants with type" + type + "  was found" + e.getMessage());
        }
    }

    @Override
    public PlantDTO add(PlantDTO plantDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant plant = new Plant(plantDTO);
            em.persist(plant);
            em.getTransaction().commit();
            return new PlantDTO(plant);
        } catch (Exception e) {
            throw new JPAException("Error adding  a new Plant faild.\n " + e.getMessage());
        }

    }

    @Override
    public List<PlantDTO> plantsWithMaxHeight(int maxHeight) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p WHERE p.maxheight = :maxHeight", Plant.class);
            query.setParameter("maxHeight", maxHeight);
            return PlantDTO.toDTOList(query.getResultList());
        } catch (Exception e) {
            throw new JPAException("ERROR, Plants with maxheight" + maxHeight + "  was found" + e.getMessage());
        }
    }

    @Override
    public List<String> getPlantNames() {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p WHERE p.name = name ", Plant.class);
            PlantDTO.toDTOList(query.getResultList());
            List<String> nameList = new ArrayList<>();
            nameList.add(query.getSingleResult().getName());
            return nameList;
        } catch (Exception e) {
            throw new JPAException("ERROR, no  Plant names  was found" + e.getMessage());
        }
    }

    @Override
    public List<PlantDTO> plantsSortedByName() {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p ORDER BY p.name ", Plant.class);
            return PlantDTO.toDTOList(query.getResultList());
        } catch (Exception e) {
            throw new JPAException("ERROR, no  Plant names  was found" + e.getMessage());
        }
    }

    public RessellerDTO createReseller(RessellerDTO ressellerDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Reseller reseller = new Reseller(ressellerDTO);
            em.persist(reseller);
            em.getTransaction().commit();
            return new RessellerDTO(reseller);
        } catch (Exception e) {
            throw new JPAException("ERROR, creating  Reseller faild" + e.getMessage());
        }
    }


    public Reseller addPlantToReseller(long resellerId, long plantId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Plant plant = em.find(Plant.class, plantId);
            Reseller reseller = em.find(Reseller.class, resellerId);

            if (plant != null && reseller != null) {

                plant.addReseller(reseller);

                em.merge(plant);
                em.merge(reseller);
                em.getTransaction().commit();
                return reseller;
            } else {
                throw new JPAException("ERROR: Plant or Reseller not found for the provided IDs.");
            }
        } catch (Exception e) {
            throw new JPAException("ERROR adding Plant " + plantId + " to Relseller");
        }

    }

    public List<PlantDTO> getPlantsByReseller(long resellerId) {
        try (EntityManager em = emf.createEntityManager()) {

            TypedQuery<PlantDTO> query = em.createQuery("SELECT new app.dto.PlantDTO(p) FROM Plant p JOIN FETCH p.resellers r WHERE r.id = :resellerId ", PlantDTO.class);

            query.setParameter("resellerId", resellerId);

            return query.getResultList();

        } catch (Exception e) {
            throw new JPAException("ERROR, no  Plant with reseller id " + resellerId + "   was found " + e.getMessage());
        }
    }

    public void deletePlant(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant plant = em.find(Plant.class, id);
            if (plant != null) {
                em.remove(plant);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new JPAException("Error deleting Plant.\n " + e.getMessage());
        }
    }
}

