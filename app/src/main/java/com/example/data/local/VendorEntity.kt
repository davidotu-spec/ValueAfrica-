package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bookmarked_vendors")
data class BookmarkedVendor(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val location: String,
    val description: String,
    val rating: Double,
    val isBookmarked: Boolean = true
)

@Entity(tableName = "vendor_applications")
data class VendorApplication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val businessName: String,
    val category: String,
    val businessAddress: String,
    val contactEmail: String,
    val productDescription: String,
    val cacNumber: String,
    val status: String = "Draft",
    val timestamp: Long = System.currentTimeMillis()
)

@Dao
interface VendorDao {
    @Query("SELECT * FROM bookmarked_vendors")
    fun getBookmarkedVendors(): Flow<List<BookmarkedVendor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(vendor: BookmarkedVendor)

    @Query("DELETE FROM bookmarked_vendors WHERE id = :id")
    suspend fun removeBookmarkById(id: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_vendors WHERE id = :id)")
    fun isVendorBookmarked(id: String): Flow<Boolean>

    @Query("SELECT * FROM vendor_applications ORDER BY timestamp DESC")
    fun getVendorApplications(): Flow<List<VendorApplication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendorApplication(app: VendorApplication)

    @Query("DELETE FROM vendor_applications WHERE id = :id")
    suspend fun deleteApplicationById(id: Int)
}
