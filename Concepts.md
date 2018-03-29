## Concepts

1. Make a subjects node under the root child node and store date wise attendance information under that for at a glance viewing of class-wise attendance of that subject.

   ### Database Structure

   ```json
   "Subjects" : {
       "IT801" : {
           "22-03-2018" : 20,
           "24-03-2018" : 12
       },
       "IT802" : {
           "20-03-2018" : 13,
           "21-03-2018" : 18
       }
   }
   ```

   ### Concept method

   ```java
   public void updateSubjectAttendance(final String date, final String classId, final boolean status) {
       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Subjects");
       reference.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
           public void onDataChange(Datasnapshot dataSnapshot) {
               if(dataSnapshot.hasChild(classId)) {
                   if(dataSnapshot.child(classId).hasChild(date)) {
                       count = (long) dataSnapshot.child(classId).child(date).getValue();
                       if(status) {
                           reference.child(classId).child(date).setValue(count + 1);
                       } else {
                           reference.child(classId).child(date).setValue(count - 1);
                       }
                   } else {
                       if(status) {
                           reference.child(classId).child(date).setValue(1);
                       } else {
                           reference.child(classId).child(date).setValue(0);
                       }
                   }
               } else {
                   if(status) {
                       reference.child(classId).child(date).setValue(1);
                   } else {
                       reference.child(classId).child(date).setValue(0);
                   }
               }
           }
           
           @Override
           public void onCancelled(DatabaseError databaseError) {
               
           }
       });
   }
   ```
2. ---