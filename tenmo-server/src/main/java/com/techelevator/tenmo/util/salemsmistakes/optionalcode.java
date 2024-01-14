package com.techelevator.tenmo.util.salemsmistakes;

public class optionalcode {

    /*

    transferDao
      String createTransfer(int accountFrom, int accountTo, BigDecimal amount, int transferId);


    String updateTransferRequests(int statusId);
    String transferMoney(int accountFrom, int accountTo, BigDecimal amount);

    boolean acceptTransfer(int transferId);
    boolean denyTransfer(int transferId);

     */
    /*
    jdbctransferdao

    @Override
//    public List<Transfer> getAllPendingTransfersByUserId(int userId) {
//        List<Transfer> transferList = new ArrayList<>();
//        Transfer transfer;
//        String sql = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfer t " +
//                "JOIN account a ON t.account_from = a.account_id " +
//                "JOIN account b ON t.account_to = b.account_id " +
//                "JOIN tenmo_users u ON a.user_id = u.user_id " +
//                "JOIN tenmo_user v ON b.user_id = v.user_id " +
//                "WHERE transfer_status_id = 1 AND (account_from = ? OR account_to = ?)";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId); //edit to fit the sql logic
//            if(results.next()){
//                transfer= mapRowToCard(results);
//                transferList.add(transfer);
//            }
//        }catch(DataAccessException e){
//            throw new DaoException("Could not access database.");
//        }
//        return transferList;
//    }

    @Override
    public String createTransfer(int accountFrom, int accountTo, BigDecimal amount) {
        if (accountFrom == accountTo) {
            return "You can't send money to yourself.";
        }
        if (accountFrom <= 0 || accountTo <= 0) {
            throw new IllegalArgumentException("Account id cannot be less than 0.");
        }
        BigDecimal accountFromBalance = accountDao.getBalanceByAccountId(accountFrom);
        if (accountFromBalance.compareTo(amount) >= 0) {
            String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES (2, 2, ?, ?, ?)";
            jdbcTemplate.update(sql, accountFrom, accountTo, amount);
            accountDao.subtractBalanceByAccountId(amount, accountFrom);
            accountDao.addToBalanceByUserId(amount, accountTo);
            return "Transfer complete";
        }else{
            return "Transfer failed due to lack of funds.";
        }

    }
     */
    /*
    transfercontroller

    @PostMapping("/create")
    public ResponseEntity<String> createTransfer(@RequestBody Transfer transferRequest){
        String result = transferDao.createTransfer(transferRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/accept/{transferId}")
    public ResponseEntity<String>acceptTransfer(@PathVariable int transferId) {
        //accepts a transfer based on provided transferId
        try {
            boolean result = transferDao.acceptTransfer(transferId);
            if (result) {
                return ResponseEntity.ok("Transfer accepted successfully!");
            } else {
                return ResponseEntity.ok("Failed to accept your transfer.");
            }
        } catch (DaoException e) {
            BasicLogger.log("Error retrieving transfers: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);//not sure about exceptions
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/deny/{transferId}")
    public ResponseEntity<String> denyTransfer(@PathVariable int transferId) {
        //denies transfer based on provided transferId
        try {
            boolean result = transferDao.denyTransfer(transferId);
            if (result) {
                return ResponseEntity.ok("Transfer denied successfully");
            } else {
                return ResponseEntity.ok("Failed to deny the transfer");
            }
        } catch (DaoException e) {
            BasicLogger.log("Error retrieving transfers: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);//not sure about exceptions
        }
    }

    @GetMapping("/pending/{userId}")
//    public ResponseEntity<List<Transfer>>getAllPendingTransfersByUserId(@PathVariable Integer userId) {
//        // retrieves list of pending transfers for specific user
//        try {
//            List<Transfer> pendingTransfer = transferDao.getAllPendingTransfersByUserId(userId);
//            return ResponseEntity.ok(pendingTransfer);
//        } catch (DaoException e) {
//            BasicLogger.log("Error retrieving transfers: " + e.getMessage());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //not sure about exceptions
//        }
//    }
     */

//transfer service

    //    public String createTransfer(Transfer transferRequest){ //added method
//        try{
//            String url = urlBaseTransfer + "/send";
//            ResponseEntity<String> response = restTemplate.postForEntity(url, transferRequest, String.class);
//            return response.getBody();
//        }catch(RestClientResponseException | ResourceAccessException e){
//            BasicLogger.log("Error sending transfer:" + e.getMessage());
//        }
//        return null;
//    }

//    public String acceptTransfer(int transferId) { //accepts a transfer by making post request
//        try {  //transferId parameter identifies the transfer to be accepted
//            String url = urlBaseTransfer + "/transfers/accept/{transferId}";
//            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, transferId);
//            return response.getBody();
//        }catch(RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log("Error accepting transfer: " + e.getMessage());
//        } return null;
//    }
//
//    public String denyTransfer(int transferId) { //denies transfer by making post request
//        try { //transferId parameter identifies the transfer to be denied
//            String url = urlBaseTransfer + "/transfers/deny/{transferId}";
//            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class, transferId);
//            return response.getBody();
//        }catch(RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log("Error denying transfer: " + e.getMessage());
//        } return null;
//    }
}
