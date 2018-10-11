package com.metallica.tradingService.etc;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.metallica.tradingService.entities.TradingEntity;


public class SearchInfo {

	private Date startDate; //start of date range of search
	private Date endDate; //end of date range of search
	private String comm; //commodity name/symbol
	private String loc; //location
	private String counterParty; //name of counter party of trade
	private Side side; //buy or sell side


	/****************************CONSTRUCTORS*********************************/
	/**
	 * Default constructor
	 */
	public SearchInfo() {
		super();
	}

	/**
	 * @param startDate: start of date range of search
	 * @param endDate: end of date range of search
	 * @param comm: commodity name/symbol
	 * @param loc: location
	 * @param counterParty: name of counter party of trade
	 * @param side: buy or sell side
	 */
	public SearchInfo(Date startDate, Date endDate, String comm, String loc,
			String counterParty, Side side) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.comm = comm;
		this.loc = loc;
		this.counterParty = counterParty;
		this.side = side;
	}


	/******************************GETTERS************************************/
	/**
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the commodity
	 */
	public String getComm() {
		return comm;
	}

	/**
	 * @return the location
	 */
	public String getLoc() {
		return loc;
	}

	/**
	 * @return the counter party
	 */
	public String getCounterParty() {
		return counterParty;
	}

	/**
	 * @return the buy or sell side
	 */
	public Side getSide() {
		return side;
	}


	/*******************************SETTERS***********************************/
	/**
	 * @param the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param the comm to set
	 */
	public void setComm(String comm) {
		this.comm = comm;
	}

	/**
	 * @param the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}

	/**
	 * @param the counterParty to set
	 */
	public void setCounterParty(String counterParty) {
		this.counterParty = counterParty;
	}

	/**
	 * @param the side to set
	 */
	public void setSide(Side side) {
		this.side = side;
	}


	/*******************************METHODS***********************************/
	@Override
	public String toString() {
		return "SearchInfo [startDate=" + startDate + ", endDate=" + endDate +
				", comm=" + comm + ", loc=" + loc + ", counterParty=" + 
				counterParty + ", side=" + side + "]";
	}
	
	

	/**Makes sure that the date range entered is valid.
	 * 
	 * @return true if the start date comes before the end date.
	 */
	public boolean validDate() { 
		if(this.getStartDate() != null || this.getEndDate() != null) {
			if (this.getStartDate().compareTo(this.getEndDate())==1) {
				return false;
			}
		}
		return true;
	}

	/**takes the parameter search and converts it to specification of search 
	 * criteria.
	 * 
	 * @param search
	 * @return
	 */
	public static Specification<TradingEntity> searchCriteria(
			SearchInfo search){

		return new Specification<TradingEntity>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<TradingEntity> root, 
					CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				//checks if user entered a commodity in search
				if(search.getComm() != null) {
					predicates.add(criteriaBuilder.equal(root.get("commodity"),
							search.getComm()));
				}

				//checks if user entered a location in search
				if(search.getLoc() != null) {
					predicates.add(criteriaBuilder.equal(root.get("location"), 
							search.getLoc()));
				}

				//checks if user entered a counter party in search
				if(search.getCounterParty() != null) {
					predicates.add(criteriaBuilder.equal(
							root.get("counterParty"),
							search.getCounterParty()));
				}

				//checks if user entered a side in search
				if(search.getSide() != null) {
					predicates.add(criteriaBuilder.equal(root.get("side"),
							search.getSide()));
				}

				//checks if user entered a date range in search
				if(search.getStartDate() != null &&
						search.getEndDate() != null) {
					predicates.add(criteriaBuilder.between(root.get("date"), 
							search.getStartDate(), search.getEndDate()));
				}

				return criteriaBuilder.and(predicates.toArray(
						new Predicate[] {}));
			}
		};

	}

}
